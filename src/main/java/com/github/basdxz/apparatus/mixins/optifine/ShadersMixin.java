package com.github.basdxz.apparatus.mixins.optifine;

import com.github.basdxz.apparatus.defenition.managed.IParaItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.val;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Client-Side
@SuppressWarnings({"MixinAnnotationTarget", "UnresolvedMixinReference"}) // Hooks into class not present in dev.
@Mixin(targets = "shadersmod.client.Shaders", remap = false)
public class ShadersMixin {

    @Inject(method = "isTranslucentBlock(Lnet/minecraft/item/ItemStack;)Z",
            at = @At(value = "HEAD"),
            cancellable = true,
            require = 1)
    @SideOnly(Side.CLIENT)
    private static void isTranslucentBlockRedirect(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if (itemStack == null)
            return;
        val item = itemStack.getItem();
        if (!(item instanceof IParaItemBlock))
            return;
        cir.setReturnValue(((IParaItemBlock) item).paraTile(itemStack).getRenderBlockPass() == 1);
        cir.cancel();
    }
}
