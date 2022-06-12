package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.resource.ISpriteModel;
import com.github.basdxz.apparatus.common.resource.impl.ModelProperties;
import com.github.basdxz.apparatus.common.resource.impl.SpriteModel;
import com.github.basdxz.apparatus.common.resource.impl.TextureResource;
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
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.lwjgl.opengl.*;

import java.awt.*;

import static com.github.basdxz.apparatus.cool.TempRenderItemOld.RES_ITEM_GLINT;
import static net.minecraft.client.Minecraft.getMinecraft;

@SideOnly(Side.CLIENT)
@RequiredArgsConstructor
public class ParaItemRendererWrapperOld implements IItemRenderer {
    protected final static float ITEM_UNIT_THICKNESS = 0.0625F;

    protected static IIcon NULL_TEXTURE_REFERENCE;

    private final boolean renderWithColor = false;
    private final TempRenderItemOld renderItem = new TempRenderItemOld();

    protected IIcon bottom;
    protected IIcon middle;
    protected IIcon top;

    protected ISpriteModel sprite;

    {
        renderItem.setRenderManager(RenderManager.instance);

        val props = new ModelProperties(
                "apparatus",
                "prop",
                true,
                true,
                true,
                Color.WHITE,
                new Vector3f(),
                new Quaternionf(),
                new Vector3f()
        );
        val tex = new TextureResource(
                "apparatus",
                "SwInner"
        );
        sprite = new SpriteModel(props, 1F, tex);
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
        //renderThick(nullTexture(), 1F / 16F);
//        GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
//        GL11.glScalef(0.625F, -0.625F, 0.625F);
//        GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
//        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        renderTestCircuit();
    }

    protected void renderAsEquippedFirstPerson(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase) {
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
        GL11.glDisable(GL11.GL_LIGHTING); //Forge: Make sure that render states are reset, a renderEffect can derp them up.
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        renderFlat(bottom);
        renderFlat(middle);
        renderFlat(top);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
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
