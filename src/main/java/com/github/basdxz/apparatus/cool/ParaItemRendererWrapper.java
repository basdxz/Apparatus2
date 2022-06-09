package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.parathing.ParaItemRender;
import lombok.*;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

import static com.github.basdxz.apparatus.common.popoga.impl.RenderView.*;

@RequiredArgsConstructor
public class ParaItemRendererWrapper implements IItemRenderer {
    protected final static float ITEM_UNIT_THICKNESS = 0.0625F;

    @NonNull
    protected final ParaItemRender render;

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    //TODO: Add missing render helper flags
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        switch (type) {
            case ENTITY:
                switch (helper) {
                    case ENTITY_BOBBING:
                    case ENTITY_ROTATION:
                        return true;
                }
        }
        return false;
    }


    //TODO: Add map rendering
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
            case INVENTORY: {
                if (data[0] instanceof RenderBlocks)
                    renderInInventory((RenderBlocks) data[0]);
            }
        }
    }

    protected void renderAsEntity(@NonNull RenderBlocks renderBlocks, @NonNull EntityItem entityItem) {
        render.itemRender(ENTITY);
    }

    protected void renderAsEquipped(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase) {
        render.itemRender(EQUIPPED);
    }

    protected void renderAsEquippedFirstPerson(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase) {
        render.itemRender(EQUIPPED_FIRST_PERSON);
    }

    protected void renderInInventory(@NonNull RenderBlocks renderBlocks) {
        render.itemRender(INVENTORY);
    }

    protected static void renderThick(@NonNull IIcon icon, float thickness) {
        ItemRenderer.renderItemIn2D(
                Tessellator.instance,
                icon.getMaxU(), icon.getMinV(),
                icon.getMinU(), icon.getMaxV(),
                icon.getIconWidth(), icon.getIconHeight(),
                ITEM_UNIT_THICKNESS * thickness);
    }

    protected static void renderFlat(@NonNull IIcon icon) {
        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.addVertexWithUV(0, 16, 0, icon.getMinU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 16, 0, icon.getMaxU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 0, 0, icon.getMaxU(), icon.getMinV());
        Tessellator.instance.addVertexWithUV(0, 0, 0, icon.getMinU(), icon.getMinV());
        Tessellator.instance.draw();
    }

    public void registerResources(@NonNull IIconRegister register) {

    }
}
