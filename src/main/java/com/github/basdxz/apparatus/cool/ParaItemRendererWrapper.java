package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.parathing.ParaItemRender;
import lombok.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.*;

import java.util.Random;

import static com.github.basdxz.apparatus.common.popoga.impl.RenderView.*;
import static net.minecraft.client.Minecraft.getMinecraft;
import static net.minecraft.client.renderer.entity.RenderItem.renderInFrame;

@RequiredArgsConstructor
public class ParaItemRendererWrapper implements IItemRenderer {
    protected static IIcon NULL_TEXTURE_REFERENCE;
    protected final static float ITEM_UNIT_THICKNESS = 0.0625F;

    private final boolean renderWithColor = false;
    private final Random random = new Random();
    @NonNull
    protected final ParaItemRender render;

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
        //render.itemModels(ENTITY);

        //val itemStack = entityItem.getEntityItem();
        //if (itemStack == null)
        //    return;
        //GL11.glPushMatrix();
//
        //if (renderInFrame)
        //    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
//
        //GL11.glTranslatef(-0.5F, -0.25F, -((0.0625F + 0.021875F) / 2.0F) + 0.0625F + 0.021875F);
        //renderThick(nullTexture(), 1.0F);
        //GL11.glPopMatrix();

        doRender(entityItem);
    }

    public void doRender(@NonNull EntityItem entityItem) {
        val itemStack = entityItem.getEntityItem();
        if (itemStack.getItem() == null)
            return;

        random.setSeed(187L);
        GL11.glPushMatrix();
        float f2 = shouldBob() ? MathHelper.sin(((float) entityItem.age + 0) / 10.0F + entityItem.hoverStart) * 0.1F + 0.1F : 0F;
        float f3 = (((float) entityItem.age + 0) / 20.0F + entityItem.hoverStart) * (180F / (float) Math.PI);
        byte miniBlocks = 1;

        if (entityItem.getEntityItem().stackSize > 1) {
            miniBlocks = 2;
        }

        if (entityItem.getEntityItem().stackSize > 5) {
            miniBlocks = 3;
        }

        if (entityItem.getEntityItem().stackSize > 20) {
            miniBlocks = 4;
        }

        if (entityItem.getEntityItem().stackSize > 40) {
            miniBlocks = 5;
        }

        GL11.glTranslatef(0F, f2 * 2, 0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float f6;
        float f7;
        int k;

        float f5;

        if (renderInFrame) {
            GL11.glScalef(1.025641F, 1.025641F, 1.025641F);
            GL11.glTranslatef(0.0F, -0.05F, 0.0F);
        }

        IIcon iicon = nullTexture();

        if (this.renderWithColor) {
            int i = itemStack.getItem().getColorFromItemStack(itemStack, 0);
            float f4 = (float) (i >> 16 & 255) / 255.0F;
            f5 = (float) (i >> 8 & 255) / 255.0F;
            f6 = (float) (i & 255) / 255.0F;
            this.renderDroppedItem(entityItem, iicon, miniBlocks, f4, f5, f6);
        } else {
            this.renderDroppedItem(entityItem, iicon, miniBlocks, 1.0F, 1.0F, 1.0F);
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    private boolean shouldBob() {
        return true;
    }

    private void renderDroppedItem(EntityItem entityItem, IIcon icon, int miniBlocks, float red, float green, float blue) {
        this.renderDroppedItem(entityItem, icon, miniBlocks, red, green, blue, 0);
    }

    private void renderDroppedItem(EntityItem entityItem, IIcon icon, int miniBlocks, float red, float green, float blue, int pass) {
        Tessellator tessellator = Tessellator.instance;

        if (icon == null) {
            TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
            ResourceLocation resourcelocation = texturemanager.getResourceLocation(entityItem.getEntityItem().getItemSpriteNumber());
            icon = ((TextureMap) texturemanager.getTexture(resourcelocation)).getAtlasSprite("missingno");
        }

        float minU = icon.getMinU();
        float maxU = icon.getMaxU();
        float minV = icon.getMinV();
        float maxV = icon.getMaxV();
        float fOne = 1.0F;
        float fTwo = 0.5F;
        float fQuarter = 0.25F;
        float magicNumber;

        if (RenderManager.instance.options.fancyGraphics) {
            GL11.glPushMatrix();

            if (renderInFrame) {
                GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            } else {
                GL11.glRotatef((((float) entityItem.age + 0) / 20.0F + entityItem.hoverStart) * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
            }

            float oneOverSixteenth = 0.0625F;
            magicNumber = 0.021875F;
            ItemStack itemstack = entityItem.getEntityItem();
            int stackSize = itemstack.stackSize;
            byte miniItemCount;

            if (stackSize < 2) {
                miniItemCount = 1;
            } else if (stackSize < 16) {
                miniItemCount = 2;
            } else if (stackSize < 32) {
                miniItemCount = 3;
            } else {
                miniItemCount = 4;
            }

            GL11.glTranslatef(-fTwo, -fQuarter, -((oneOverSixteenth + magicNumber) * (float) miniItemCount / 2.0F));

            for (int k = 0; k < miniItemCount; ++k) {
                // Makes items offset when in 3D, like when in 2D, looks much better. Considered a vanilla bug...
                if (k > 0 && shouldSpreadItems()) {
                    float x = (random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
                    float y = (random.nextFloat() * 2.0F - 1.0F) * 0.3F / 0.5F;
                    GL11.glTranslatef(x, y, oneOverSixteenth + magicNumber);
                } else {
                    GL11.glTranslatef(0F, 0F, oneOverSixteenth + magicNumber);
                }

                if (itemstack.getItemSpriteNumber() == 0) {
                    this.bindTexture(TextureMap.locationBlocksTexture);
                } else {
                    this.bindTexture(TextureMap.locationItemsTexture);
                }

                GL11.glColor4f(red, green, blue, 1.0F);
                ItemRenderer.renderItemIn2D(tessellator, maxU, minV, minU, maxV, icon.getIconWidth(), icon.getIconHeight(), oneOverSixteenth);

                //if (itemstack.hasEffect(pass))
                //{
                //    GL11.glDepthFunc(GL11.GL_EQUAL);
                //    GL11.glDisable(GL11.GL_LIGHTING);
                //    this.renderManager.renderEngine.bindTexture(RES_ITEM_GLINT);
                //    GL11.glEnable(GL11.GL_BLEND);
                //    GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                //    float f11 = 0.76F;
                //    GL11.glColor4f(0.5F * f11, 0.25F * f11, 0.8F * f11, 1.0F);
                //    GL11.glMatrixMode(GL11.GL_TEXTURE);
                //    GL11.glPushMatrix();
                //    float f12 = 0.125F;
                //    GL11.glScalef(f12, f12, f12);
                //    float f13 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
                //    GL11.glTranslatef(f13, 0.0F, 0.0F);
                //    GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                //    ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, oneOverSixteenth);
                //    GL11.glPopMatrix();
                //    GL11.glPushMatrix();
                //    GL11.glScalef(f12, f12, f12);
                //    f13 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                //    GL11.glTranslatef(-f13, 0.0F, 0.0F);
                //    GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                //    ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, oneOverSixteenth);
                //    GL11.glPopMatrix();
                //    GL11.glMatrixMode(GL11.GL_MODELVIEW);
                //    GL11.glDisable(GL11.GL_BLEND);
                //    GL11.glEnable(GL11.GL_LIGHTING);
                //    GL11.glDepthFunc(GL11.GL_LEQUAL);
                //}
            }

            GL11.glPopMatrix();
        } else {
            for (int l = 0; l < miniBlocks; ++l) {
                GL11.glPushMatrix();

                if (l > 0) {
                    magicNumber = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
                    float f16 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
                    float f17 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.3F;
                    GL11.glTranslatef(magicNumber, f16, f17);
                }

                if (!renderInFrame)
                    GL11.glRotatef(180.0F - RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);

                GL11.glColor4f(red, green, blue, 1.0F);
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                tessellator.addVertexWithUV(0.0F - fTwo, 0.0F - fQuarter, 0.0D, minU, maxV);
                tessellator.addVertexWithUV(fOne - fTwo, 0.0F - fQuarter, 0.0D, maxU, maxV);
                tessellator.addVertexWithUV(fOne - fTwo, 1.0F - fQuarter, 0.0D, maxU, minV);
                tessellator.addVertexWithUV(0.0F - fTwo, 1.0F - fQuarter, 0.0D, minU, minV);
                tessellator.draw();
                GL11.glPopMatrix();
            }
        }
    }

    private boolean shouldSpreadItems() {
        return true;
    }

    protected void bindTexture(ResourceLocation resourceLocation) {
        RenderManager.instance.renderEngine.bindTexture(resourceLocation);
    }

    protected void renderAsEquipped(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase) {
        render.itemModels(EQUIPPED);
        renderThick(nullTexture(), 1.0F);
    }

    protected void renderAsEquippedFirstPerson(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase) {
        render.itemModels(EQUIPPED_FIRST_PERSON);
        renderThick(nullTexture(), 1.0F);
    }

    protected void renderInInventory(@NonNull RenderBlocks renderBlocks) {
        render.itemModels(INVENTORY);

        renderFlat(nullTexture());
    }

    //TODO: Not thread safe right now
    protected static IIcon nullTexture() {
        if (NULL_TEXTURE_REFERENCE == null)
            NULL_TEXTURE_REFERENCE = ((TextureMap) getMinecraft()
                    .getTextureManager()
                    .getTexture(TextureMap.locationItemsTexture))
//                    .getAtlasSprite("missingno");
                    .getAtlasSprite("potato");
        return NULL_TEXTURE_REFERENCE;
    }

    protected static void renderFlat(@NonNull IIcon icon) {
        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.addVertexWithUV(0, 16, 0, icon.getMinU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 16, 0, icon.getMaxU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(16, 0, 0, icon.getMaxU(), icon.getMinV());
        Tessellator.instance.addVertexWithUV(0, 0, 0, icon.getMinU(), icon.getMinV());
        Tessellator.instance.draw();
    }

    protected static void renderThick(@NonNull IIcon icon, float thickness) {
        ItemRenderer.renderItemIn2D(
                Tessellator.instance,
                icon.getMaxU(), icon.getMinV(),
                icon.getMinU(), icon.getMaxV(),
                icon.getIconWidth(), icon.getIconHeight(),
                ITEM_UNIT_THICKNESS * thickness);
    }

    protected static void renderMesh() {

    }

    public void registerResources(@NonNull IIconRegister register) {
        if (NULL_TEXTURE_REFERENCE != null)
            NULL_TEXTURE_REFERENCE = null;
    }
}
