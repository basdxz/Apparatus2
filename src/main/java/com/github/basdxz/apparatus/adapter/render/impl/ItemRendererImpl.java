package com.github.basdxz.apparatus.adapter.render.impl;

import com.github.basdxz.apparatus.adapter.item.IItemAdapter;
import com.github.basdxz.apparatus.adapter.render.IItemRendererImpl;
import com.github.basdxz.apparatus.common.render.IRenderView;
import com.github.basdxz.apparatus.common.resourceold.IRendererOld;
import lombok.*;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.MinecraftForgeClient;

import java.util.HashMap;
import java.util.Map;

import static com.github.basdxz.apparatus.common.render.impl.RenderView.*;

public class ItemRendererImpl implements IItemRendererImpl {
    protected final IItemAdapter itemAdapter;
    protected final Map<IRenderView, IRendererOld> renderers;

    protected final Map<IRenderView, RendererAdapter> adaptedRenderers = new HashMap<>();
    protected final TempRenderItemOld tempRenderItemOld = new TempRenderItemOld();

    {
        tempRenderItemOld.setRenderManager(RenderManager.instance);
    }

    public ItemRendererImpl(@NonNull IItemAdapter itemAdapter) {
        this.itemAdapter = itemAdapter;
        this.renderers = itemAdapter.item().renderersOld();
        register();
    }

    protected void register() {
        MinecraftForgeClient.registerItemRenderer(itemAdapter.minecraftItem(), this);
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        itemAdapter.item().renderer().render(TestRenderContext.INSTANCE);
//        switch (type) {
//            case ENTITY:
//                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityItem)
//                    renderAsEntity((RenderBlocks) data[0], (EntityItem) data[1]);
//                break;
//            case EQUIPPED:
//                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityLivingBase)
//                    renderAsEquipped((RenderBlocks) data[0], (EntityLivingBase) data[1]);
//                break;
//            case EQUIPPED_FIRST_PERSON:
//                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityLivingBase)
//                    renderAsEquippedFirstPerson((RenderBlocks) data[0], (EntityLivingBase) data[1]);
//                break;
//            case INVENTORY:
//                if (data[0] instanceof RenderBlocks)
//                    renderInInventory((RenderBlocks) data[0]);
//        }
    }

    protected void renderAsEntity(@NonNull RenderBlocks renderBlocks, @NonNull EntityItem entityItem) {
        tempRenderItemOld.actualRender(entityItem, this::renderAsEntity);
    }

    private void renderAsEntity() {
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

    @Override
    public void register(@NonNull IIconRegister iconRegister) {
        adaptedRenderers.clear();
        for (val entry : renderers.entrySet()) {
            val view = entry.getKey();
            val renderer = entry.getValue();
            if (view == ENTITY)
                adaptedRenderers.put(ENTITY, new RendererAdapter(renderer, iconRegister));
        }
    }

    @Override
    public IIcon fallbackIcon() {
        return null;
    }
}
