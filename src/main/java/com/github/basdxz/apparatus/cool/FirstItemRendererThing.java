package com.github.basdxz.apparatus.cool;

import com.falsepattern.dynamicrendering.drawing.MeshRenderer;
import lombok.*;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.*;

@RequiredArgsConstructor
public class FirstItemRendererThing implements IItemRenderer {
    private final static float ITEM_2D_THICKNESS = 0.0625F;

    protected final ParaItemWrapper paraItemWrapper;

    protected final MeshRenderer mRender = new MeshRenderer() {
        {
            field_147501_a = TileEntityRendererDispatcher.instance;
        }
    };

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


    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glPushMatrix();

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.0001F);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

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

        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    protected void renderAsEntity(@NonNull RenderBlocks renderBlocks, @NonNull EntityItem entityItem) {
        GL11.glTranslatef(-0.5F, -0.25F, 0F); //TODO What exactly -is- this offset?

//        val offset = 0.025F;
//        renderIconWithThickness(paraItemWrapper.inner);
//        GL11.glTranslatef(0F, 0F, offset);
//        renderIconWithThickness(paraItemWrapper.outer);
//        GL11.glTranslatef(0F, 0F, -offset * 2);
//        renderIconWithThickness(paraItemWrapper.outer);
//        GL11.glTranslatef(0F, 0F, offset);

//                    GL11.glColor4f(0.72F, 0.45F, 0.2F, 1F);
//                    renderIconWithThickness(paraItemWrapper.geauh);
//
//                    paraItemWrapper.prism.draw(mRender, 0, 0,0);
    }

    private void renderAsEquipped(RenderBlocks renderBlocks, EntityLivingBase entityLivingBase) {
        System.out.println(renderBlocks);
        System.out.println(entityLivingBase);
//        val offset = 0.025F;
//        renderIconWithThickness(paraItemWrapper.inner);
//        GL11.glTranslatef(0F, 0F, offset);
//        renderIconWithThickness(paraItemWrapper.outer);
//        GL11.glTranslatef(0F, 0F, -offset * 2);
//        renderIconWithThickness(paraItemWrapper.outer);
//        GL11.glTranslatef(0F, 0F, offset);

//                    GL11.glColor4f(0.72F, 0.45F, 0.2F, 1F);
//                    renderIconWithThickness(paraItemWrapper.geauh);
//
//                    paraItemWrapper.prism.draw(mRender, 0, 0,0);
    }

    private void renderAsEquippedFirstPerson(RenderBlocks renderBlocks, EntityLivingBase entityLivingBase) {
//        val offset = 0.025F;
//        renderIconWithThickness(paraItemWrapper.inner);
//        GL11.glTranslatef(0F, 0F, offset);
//        renderIconWithThickness(paraItemWrapper.outer);
//        GL11.glTranslatef(0F, 0F, -offset * 2);
//        renderIconWithThickness(paraItemWrapper.outer);
//        GL11.glTranslatef(0F, 0F, offset);

//                    GL11.glColor4f(0.72F, 0.55F, 0.2F, 0.5F);
//                    renderIconWithThickness(paraItemWrapper.geauh);
//
//                    paraItemWrapper.prism.draw(mRender, 0, 0,0);
    }

    private void renderInInventory(RenderBlocks renderBlocks) {
//        System.out.println(renderBlocks);
//        renderIconFlat(paraItemWrapper.inner);
//        renderIconFlat(paraItemWrapper.outer);
//        renderIconFlat(paraItemWrapper.outer);

//                    GL11.glColor4f(0.72F, 0.45F, 0.2F, 1F);
//                    renderIconFlat(paraItemWrapper.geauh);
//
//                    paraItemWrapper.prism.draw(mRender, 0, 0,0);
    }

    public static void renderIconWithThickness(@NonNull IIcon icon) {
        ItemRenderer.renderItemIn2D(
                Tessellator.instance,
                icon.getMaxU(), icon.getMinV(),
                icon.getMinU(), icon.getMaxV(),
                icon.getIconWidth(), icon.getIconHeight(),
                ITEM_2D_THICKNESS);
    }

    public static void renderIconFlat(@NonNull IIcon icon) {
        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.addVertexWithUV(0, 16, 0, icon.getMinU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 16, 0, icon.getMaxU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 0, 0, icon.getMaxU(), icon.getMinV());
        Tessellator.instance.addVertexWithUV(0, 0, 0, icon.getMinU(), icon.getMinV());
        Tessellator.instance.draw();
    }
}
