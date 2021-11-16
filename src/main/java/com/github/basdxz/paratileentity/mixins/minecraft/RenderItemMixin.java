package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.util.Utils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderItem.class)
public class RenderItemMixin {
    @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/RenderBlocks;renderBlockAsItem " +
                            "(Lnet/minecraft/block/Block;IF)V"),
            require = 1)
    private void doRender(EntityItem entityItem, double f7, double l, double block, float f9, float k,
                          CallbackInfo ci) {
        Utils.bufferParaTile(entityItem.getEntityItem());
    }

    @Inject(method = "renderItemIntoGUI(Lnet/minecraft/client/gui/FontRenderer;" +
            "Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IIZ)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/RenderBlocks;renderBlockAsItem " +
                            "(Lnet/minecraft/block/Block;IF)V"),
            require = 1)
    private void renderItemIntoGUI(FontRenderer p_77015_1_, TextureManager p_77015_2_, ItemStack itemStack,
                                   int p_77015_4_, int p_77015_5_, boolean renderEffect, CallbackInfo ci) {
        Utils.bufferParaTile(itemStack);
    }
}
