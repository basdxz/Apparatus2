package com.github.basdxz.apparatus.cool;

import lombok.*;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.*;

@RequiredArgsConstructor
public class FirstItemRendererThing implements IItemRenderer {
    protected final static float ITEM_2D_THICKNESS = 0.0625F;

    protected final ParaItemWrapper paraItemWrapper;

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

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

    @SuppressWarnings("DuplicateBranchesInSwitch")
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT);
        GL11.glPushMatrix();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.0001F);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        switch (type) {
            case ENTITY: {
                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityItem) {
                    val renderBlocks = (RenderBlocks) data[0];
                    val entityItem = (EntityItem) data[1];
                    GL11.glTranslatef(-0.5F, -0.25F, 0F); //TODO What exactly -is- this offset?
                    val offset = 0.025F;
                    GL11.glTranslatef(0F, 0F, offset);
                    renderIconWithThickness(paraItemWrapper.outer);
                    GL11.glTranslatef(0F, 0F, -offset);
                    renderIconWithThickness(paraItemWrapper.inner);
                    GL11.glTranslatef(0F, 0F, -offset);
                    renderIconWithThickness(paraItemWrapper.outer);
                    GL11.glTranslatef(0F, 0F, offset);
                }
            }
            break;
            case EQUIPPED: {
                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityLivingBase) {
                    val renderBlocks = (RenderBlocks) data[0];
                    val entityLivingBase = (EntityLivingBase) data[1];
                    val offset = 0.025F;
                    GL11.glTranslatef(0F, 0F, offset);
                    renderIconWithThickness(paraItemWrapper.outer);
                    GL11.glTranslatef(0F, 0F, -offset);
                    renderIconWithThickness(paraItemWrapper.inner);
                    GL11.glTranslatef(0F, 0F, -offset);
                    renderIconWithThickness(paraItemWrapper.outer);
                    GL11.glTranslatef(0F, 0F, offset);
                }
            }
            break;
            case EQUIPPED_FIRST_PERSON: {
                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityLivingBase) {
                    val renderBlocks = (RenderBlocks) data[0];
                    val entityLivingBase = (EntityLivingBase) data[1];
                    val offset = 0.025F;
                    GL11.glTranslatef(0F, 0F, offset);
                    renderIconWithThickness(paraItemWrapper.outer);
                    GL11.glTranslatef(0F, 0F, -offset);
                    renderIconWithThickness(paraItemWrapper.inner);
                    GL11.glTranslatef(0F, 0F, -offset);
                    renderIconWithThickness(paraItemWrapper.outer);
                    GL11.glTranslatef(0F, 0F, offset);
                }
            }
            break;
            case INVENTORY: {
                if (data[0] instanceof RenderBlocks) {
                    val renderBlocks = (RenderBlocks) data[0];
                    renderIconFlat(paraItemWrapper.outer);
                    renderIconFlat(paraItemWrapper.inner);
                    renderIconFlat(paraItemWrapper.outer);
                }
            }
        }

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    protected static void renderIconWithThickness(@NonNull IIcon icon) {
        ItemRenderer.renderItemIn2D(
                Tessellator.instance,
                icon.getMaxU(), icon.getMinV(),
                icon.getMinU(), icon.getMaxV(),
                icon.getIconWidth(), icon.getIconHeight(),
                ITEM_2D_THICKNESS);
    }

    public void renderIconFlat(@NonNull IIcon icon) {
        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.addVertexWithUV(0, 16, 0, icon.getMinU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 16, 0, icon.getMaxU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 0, 0, icon.getMaxU(), icon.getMinV());
        Tessellator.instance.addVertexWithUV(0, 0, 0, icon.getMinU(), icon.getMinV());
        Tessellator.instance.draw();
    }
}
