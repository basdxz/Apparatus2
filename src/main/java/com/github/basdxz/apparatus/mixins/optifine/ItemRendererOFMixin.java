package com.github.basdxz.apparatus.mixins.optifine;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.managed.IParaItemBlock;
import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import com.github.basdxz.apparatus.util.Utils;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Client-Side
@SuppressWarnings({"MixinAnnotationTarget", "UnresolvedMixinReference"}) // Hooks into class not present in dev.
@Mixin(targets = "ItemRendererOF", remap = false)
public class ItemRendererOFMixin {
    private static IParaTile cachedParaTile;

    @Inject(method = "func_78443_a(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
            at = @At(value = "HEAD"),
            require = 1)
    private void renderItemHead(EntityLivingBase p_78443_1_, ItemStack itemStack, int p_78443_3_, CallbackInfo ci) {
        Utils.bufferParaTile(itemStack);
        val item = itemStack.getItem();
        if (item instanceof IParaItemBlock)
            cachedParaTile = ((IParaItemBlock) item).paraTile(itemStack);
    }

    @Redirect(method = "func_78443_a(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;func_149701_w ()I"),
            require = 3)
    private int getRenderBlockPassRenderItemRedirect(Block instance) {
        if (!(instance instanceof IParaBlock) || cachedParaTile == null)
            return instance.getRenderBlockPass();
        return cachedParaTile.proxyBlock().getRenderBlockPass();
    }

    @Inject(method = "func_78443_a(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;I)V",
            at = @At(value = "RETURN"),
            require = 1)
    private void renderItemReturn(EntityLivingBase p_78443_1_, ItemStack itemStack, int p_78443_3_, CallbackInfo ci) {
        cachedParaTile = null;
    }
}
