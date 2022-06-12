package com.github.basdxz.apparatus.tiger;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ParaItemRendererAdapter implements IItemRenderer {
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
}
