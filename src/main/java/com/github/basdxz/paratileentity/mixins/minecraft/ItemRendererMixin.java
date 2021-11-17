package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.util.Utils;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Client-Side
@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Inject(method = "renderItem(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;" +
            "ILnet/minecraftforge/client/IItemRenderer$ItemRenderType;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/RenderBlocks;renderBlockAsItem " +
                            "(Lnet/minecraft/block/Block;IF)V"),
            require = 1)
    private void renderItemIntoGUI(EntityLivingBase p_78443_1_, ItemStack itemStack, int p_78443_3_,
                                   IItemRenderer.ItemRenderType type, CallbackInfo ci) {
        Utils.bufferParaTile(itemStack);
    }
}
