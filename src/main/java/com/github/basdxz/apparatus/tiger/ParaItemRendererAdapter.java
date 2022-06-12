package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.resource.IRender;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

public class ParaItemRendererAdapter implements IItemRenderer {
    protected final IRender itemRenderer;

    public ParaItemRendererAdapter(@NonNull IRender itemRenderer) {
        this.itemRenderer = itemRenderer;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

    }

    public IIcon fallbackIcon() {
        return null;
    }

    public void register(@NonNull IIconRegister register) {

    }
}
