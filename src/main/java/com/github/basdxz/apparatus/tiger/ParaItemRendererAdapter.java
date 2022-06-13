package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;
import lombok.*;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import java.util.HashMap;
import java.util.Map;

import static com.github.basdxz.apparatus.common.render.impl.RendererView.*;

public class ParaItemRendererAdapter implements IItemRenderer {
    protected final Map<IRendererView, IRenderer> renderers;
    protected final Map<IRendererView, RendererAdapter> adaptedRenderers = new HashMap<>();

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
        switch (type) {
            case ENTITY:
                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityItem)
                    renderAsEntity((RenderBlocks) data[0], (EntityItem) data[1]);
                break;
            case EQUIPPED:
                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityLivingBase)
                    renderAsEquipped((RenderBlocks) data[0], (EntityLivingBase) data[1]);
                break;
            case EQUIPPED_FIRST_PERSON:
                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityLivingBase)
                    renderAsEquippedFirstPerson((RenderBlocks) data[0], (EntityLivingBase) data[1]);
                break;
            case INVENTORY:
                if (data[0] instanceof RenderBlocks)
                    renderInInventory((RenderBlocks) data[0]);
        }
    }

    protected void renderAsEntity(@NonNull RenderBlocks renderBlocks, @NonNull EntityItem entityItem) {
        adaptedRenderers.getOrDefault(ENTITY, RendererAdapter.EMPTY_INSTANCE).render();
    }

    protected void renderAsEquipped(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase) {
        adaptedRenderers.getOrDefault(EQUIPPED, RendererAdapter.EMPTY_INSTANCE).render();
    }

    protected void renderAsEquippedFirstPerson(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase) {
        adaptedRenderers.getOrDefault(EQUIPPED_FIRST_PERSON, RendererAdapter.EMPTY_INSTANCE).render();
    }

    protected void renderInInventory(@NonNull RenderBlocks renderBlocks) {
        adaptedRenderers.getOrDefault(INVENTORY, RendererAdapter.EMPTY_INSTANCE).render();
    }

    public void register(@NonNull IIconRegister iconRegister) {
        adaptedRenderers.clear();
        for (val entry : renderers.entrySet()) {
            val view = entry.getKey();
            val renderer = entry.getValue();
            if (view == ENTITY)
                adaptedRenderers.put(ENTITY, new RendererAdapter(renderer, iconRegister));
        }
    }

    public IIcon fallbackIcon() {
        return null;
    }
}
