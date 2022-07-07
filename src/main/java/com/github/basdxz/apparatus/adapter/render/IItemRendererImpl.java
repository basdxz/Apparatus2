package com.github.basdxz.apparatus.adapter.render;

import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public interface IItemRendererImpl extends IItemRenderer {
    @Override
    default boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    default boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    void register(@NonNull IIconRegister iconRegister);

    IIcon fallbackIcon();
}
