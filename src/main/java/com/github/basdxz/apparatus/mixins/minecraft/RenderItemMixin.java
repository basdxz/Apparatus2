package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.managed.IParaItemBlock;
import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import com.github.basdxz.apparatus.util.Utils;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Client-Side
@Mixin(RenderItem.class)
public class RenderItemMixin {
    private static IParaTile cachedParaTile;

    @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V",
            at = @At(value = "HEAD"),
            require = 1)
    private void doRenderHead(EntityItem entityItem, double f7, double l, double block, float f9, float k,
                              CallbackInfo ci) {
        Utils.bufferParaTile(entityItem.getEntityItem());
        val itemStack = entityItem.getEntityItem();
        val item = itemStack.getItem();
        if (item instanceof IParaItemBlock)
            cachedParaTile = ((IParaItemBlock) item).paraTile(itemStack);
    }

    @Redirect(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getRenderBlockPass ()I"),
            require = 2)
    private int getRenderBlockPassDoRenderRedirect(Block instance) {
        if (!(instance instanceof IParaBlock) || cachedParaTile == null)
            return instance.getRenderBlockPass();
        return cachedParaTile.proxyBlock().getRenderBlockPass();
    }

    @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityItem;DDDFF)V",
            at = @At(value = "RETURN"),
            require = 1)
    private void doRenderReturn(EntityItem entityItem, double f7, double l, double block, float f9, float k,
                                CallbackInfo ci) {
        cachedParaTile = null;
    }

    @Inject(method = "renderItemIntoGUI(Lnet/minecraft/client/gui/FontRenderer;" +
            "Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IIZ)V",
            at = @At(value = "HEAD"),
            remap = false,
            require = 1)
    private void renderItemIntoGUIHead(FontRenderer l, TextureManager f, ItemStack itemStack, int f4, int iicon,
                                       boolean i1, CallbackInfo ci) {
        Utils.bufferParaTile(itemStack);
        val item = itemStack.getItem();
        if (item instanceof IParaItemBlock)
            cachedParaTile = ((IParaItemBlock) item).paraTile(itemStack);
    }

    @Redirect(method = "renderItemIntoGUI(Lnet/minecraft/client/gui/FontRenderer;" +
            "Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IIZ)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getRenderBlockPass ()I"),
            require = 2)
    private int getRenderBlockPassRenderIntoGUIRedirect(Block instance) {
        if (!(instance instanceof IParaBlock) || cachedParaTile == null)
            return instance.getRenderBlockPass();
        return cachedParaTile.proxyBlock().getRenderBlockPass();
    }

    @Inject(method = "renderItemIntoGUI(Lnet/minecraft/client/gui/FontRenderer;" +
            "Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/item/ItemStack;IIZ)V",
            at = @At(value = "RETURN"),
            remap = false,
            require = 1)
    private void renderItemIntoGUIReturn(FontRenderer l, TextureManager f, ItemStack itemStack, int f4, int iicon,
                                         boolean i1, CallbackInfo ci) {
        cachedParaTile = null;
    }
}
