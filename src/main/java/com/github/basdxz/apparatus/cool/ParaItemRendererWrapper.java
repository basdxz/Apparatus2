package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.parathing.ParaItemRender;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.*;

import static com.github.basdxz.apparatus.common.popoga.impl.RenderView.*;
import static com.github.basdxz.apparatus.cool.TempRenderItem.RES_ITEM_GLINT;
import static net.minecraft.client.Minecraft.getMinecraft;

@SideOnly(Side.CLIENT)
@RequiredArgsConstructor
public class ParaItemRendererWrapper implements IItemRenderer {
    protected final static float ITEM_UNIT_THICKNESS = 0.0625F;

    protected static IIcon NULL_TEXTURE_REFERENCE;

    private final boolean renderWithColor = false;
    private final TempRenderItem renderItem = new TempRenderItem();
    @NonNull
    protected final ParaItemRender render;

    protected IIcon bottom;
    protected IIcon middle;
    protected IIcon top;

    {
        renderItem.setRenderManager(RenderManager.instance);
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
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

    //Iterate over the models
    //Set the flags for each model
    //Render each model (flat/thick/mesh)
    protected void renderAsEntity(@NonNull RenderBlocks renderBlocks, @NonNull EntityItem entityItem) {
//        renderItem.doRender(entityItem, 0, 0, 0, 0, getSubTick());
//        val ent = new EntityItem(getMinecraft().theWorld, 0F, 0F, 0F, new ItemStack(Blocks.tnt));
//        ent.age = entityItem.age;
//        ent.hoverStart = entityItem.hoverStart;
        renderItem.actualRender(entityItem, this::renderTestCircuit);
    }

    protected void renderAsEquipped(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase) {
        render.itemModels(EQUIPPED);
        //renderThick(nullTexture(), 1F / 16F);
        renderTestCircuit();
    }

    protected void renderAsEquippedFirstPerson(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase) {
        render.itemModels(EQUIPPED_FIRST_PERSON);
        //renderThick(nullTexture(), 1F / 16F);
        renderTestCircuit();
    }

    protected void renderTestCircuit() {
        val unit = 1F / 16F;
        val bottomThickness = unit * 0.15F;
        val middleThickness = unit * 0.3F;
        val topThickness = unit * 0.5F;
        GL11.glPushMatrix();
        renderThick(bottom, bottomThickness);
        GL11.glTranslatef(0F, 0F, -bottomThickness);
        renderThick(middle, middleThickness);
        GL11.glTranslatef(0F, 0F, -middleThickness);
        renderThick(top, topThickness);
        GL11.glPopMatrix();
    }

    protected void renderInInventory(@NonNull RenderBlocks renderBlocks) {
        render.itemModels(INVENTORY);
        renderFlat(bottom);
        renderFlat(middle);
        renderFlat(top);
    }

    //TODO: Not thread safe right now
    protected static IIcon nullTexture() {
        if (NULL_TEXTURE_REFERENCE == null)
            NULL_TEXTURE_REFERENCE = ((TextureMap) getMinecraft()
                    .getTextureManager()
                    .getTexture(TextureMap.locationItemsTexture))
//                    .getAtlasSprite("missingno");
                    .getAtlasSprite("carrot");
        return NULL_TEXTURE_REFERENCE;
    }

    protected static void renderFlat(@NonNull IIcon icon) {
        GL11.glPushMatrix();
        GL11.glScalef(16F, 16F, 1F);
        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.addVertexWithUV(0F, 1F, 0F, icon.getMinU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(1F, 1F, 0F, icon.getMaxU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(1F, 0F, 0F, icon.getMaxU(), icon.getMinV());
        Tessellator.instance.addVertexWithUV(0F, 0F, 0F, icon.getMinU(), icon.getMinV());
        Tessellator.instance.draw();
        GL11.glPopMatrix();
    }

    protected static void renderThick(@NonNull IIcon icon, float thickness) {
        ItemRenderer.renderItemIn2D(
                Tessellator.instance,
                icon.getMaxU(), icon.getMinV(),
                icon.getMinU(), icon.getMaxV(),
                icon.getIconWidth(), icon.getIconHeight(),
                thickness);

        if (true)
            return;

        GL11.glDepthFunc(GL11.GL_EQUAL);
        GL11.glDisable(GL11.GL_LIGHTING);
        RenderManager.instance.renderEngine.bindTexture(RES_ITEM_GLINT);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
        float f11 = 0.76F;
        GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1F);
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glPushMatrix();
        GL11.glScalef(0.125F, 0.125F, 0.125F);
        float f13 = (float) (Minecraft.getSystemTime() % 3000L) / 3000F * 8F;
        GL11.glTranslatef(f13, 0F, 0F);
        GL11.glRotatef(-50F, 0F, 0F, 1F);
        ItemRenderer.renderItemIn2D(Tessellator.instance, 0F, 0F, 1F, 1F, 255, 255, thickness);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef(0.125F, 0.125F, 0.125F);
        f13 = (float) (Minecraft.getSystemTime() % 4873L) / 4873F * 8F;
        GL11.glTranslatef(-f13, 0F, 0F);
        GL11.glRotatef(10F, 0F, 0F, 1F);
        ItemRenderer.renderItemIn2D(Tessellator.instance, 0F, 0F, 1F, 1F, 255, 255, thickness);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glColor4f(1F, 1F, 1F, 1F);
    }

    protected static void renderMesh() {

    }

    public void registerResources(@NonNull IIconRegister register) {
        if (NULL_TEXTURE_REFERENCE != null)
            NULL_TEXTURE_REFERENCE = null;

        top = register.registerIcon("apparatus:TopLayer");
        middle = register.registerIcon("apparatus:MiddleLayer");
        bottom = register.registerIcon("apparatus:BottomLayer");
    }
}
