package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import java.util.HashMap;
import java.util.Map;

public class ParaItemRendererAdapter implements IItemRenderer {
    protected final Map<IRendererView, IRenderer> renderers;
    protected final Map<ItemRenderType, Object> adaptedRenderers = new HashMap<>();

    public ParaItemRendererAdapter(@NonNull Map<IRendererView, IRenderer> renderers) {
        this.renderers = renderers;
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
