package com.github.basdxz.apparatus.adapter.render.impl;

import com.github.basdxz.apparatus.adapter.render.IRendererAdapter;
import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.*;

import java.util.Random;
import java.util.concurrent.Callable;

import static com.falsepattern.lib.util.RenderUtil.partialTick;


public class TestRenderItem extends RenderItem {
    protected static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    protected final RenderBlocks renderBlocks = new RenderBlocks();
    protected final Random random = new Random();

    public void actualRender(@NonNull EntityItem entityItem, @NonNull IRendererAdapter renderer) {
        if (renderInFrame) {
            GL11.glScalef(1.025641F, 1.025641F, 1.025641F);
            GL11.glTranslatef(0F, 0.15F, 0F);
        } else {
            random.setSeed(187L);
            GL11.glTranslatef(0, entityBob(entityItem, partialTick()), 0);
        }

        renderDroppedItem(entityItem, renderer);
    }

    public void doRender(EntityItem entityItem, double posX, double posY, double posZ, float p_76986_8_, float subTick) {
        val itemStack = entityItem.getEntityItem();
        if (itemStack.getItem() == null)
            return;

        //actualRender(entityItem);
        if (true)
            return;

        bindEntityTexture(entityItem);
        TextureUtil.func_152777_a(false, false, 1.0F);
        random.setSeed(187L);
        GL11.glPushMatrix();
        GL11.glScalef(2F, 2F, 2F);
        val miniBlockCount = getMiniBlockCount(itemStack.stackSize);

        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        if (itemStack.getItemSpriteNumber() == 0 && itemStack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemStack.getItem()).getRenderType())) {
            Block block = Block.getBlockFromItem(itemStack.getItem());
            GL11.glRotatef(entitySpin(entityItem, subTick), 0.0F, 1.0F, 0.0F);

            if (renderInFrame) {
                GL11.glScalef(1.25F, 1.25F, 1.25F);
                GL11.glTranslatef(0.0F, 0.05F, 0.0F);
                GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            float scale;
            val renderType = block.getRenderType();
            if (renderType == 1 || renderType == 19 || renderType == 12 || renderType == 2) {
                scale = 0.5F;
            } else {
                scale = 0.25F;
            }

            if (block.getRenderBlockPass() > 0) {
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            }

            GL11.glScalef(scale, scale, scale);

            for (int i = 0; i < miniBlockCount; ++i) {
                GL11.glPushMatrix();

                if (i > 0) {
                    val xOffset = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / scale;
                    val yOffset = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / scale;
                    val zOffset = (random.nextFloat() * 2.0F - 1.0F) * 0.2F / scale;
                    GL11.glTranslatef(xOffset, yOffset, zOffset);
                }

                renderBlocks.renderBlockAsItem(block, itemStack.getItemDamage(), 1.0F);
                GL11.glPopMatrix();
            }

            if (block.getRenderBlockPass() > 0)
                GL11.glDisable(GL11.GL_BLEND);

        } else {
            if (itemStack.getItem().requiresMultipleRenderPasses()) {
                if (renderInFrame) {
                    GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
                    GL11.glTranslatef(0.0F, -0.05F, 0.0F);
                } else {
                    GL11.glScalef(0.5F, 0.5F, 0.5F);
                }

                for (int i = 0; i < itemStack.getItem().getRenderPasses(itemStack.getItemDamage()); ++i) {
                    random.setSeed(187L);
                    val icon = itemStack.getItem().getIcon(itemStack, i);

                    renderDroppedItem(entityItem, () -> {
                    });
                }
            } else {
                if (itemStack.getItem() instanceof ItemCloth) {
                    GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                    GL11.glEnable(GL11.GL_BLEND);
                    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                }

                if (renderInFrame) {
                    GL11.glScalef(0.5128205F, 0.5128205F, 0.5128205F);
                    GL11.glTranslatef(0.0F, -0.05F, 0.0F);
                } else {
                    GL11.glScalef(0.5F, 0.5F, 0.5F);
                }

                val icon = itemStack.getIconIndex();
                renderDroppedItem(entityItem, () -> {
                });

                if (itemStack.getItem() instanceof ItemCloth)
                    GL11.glDisable(GL11.GL_BLEND);
            }
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        bindEntityTexture(entityItem);
        TextureUtil.func_147945_b();
    }

    protected ResourceLocation getEntityTexture(EntityItem entityItem) {
        return RenderManager.instance.renderEngine.getResourceLocation(entityItem.getEntityItem().getItemSpriteNumber());
    }

    private void renderDroppedItem(@NonNull EntityItem entityItem, @NonNull IRendererAdapter renderer) {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        ResourceLocation resourcelocation = texturemanager.getResourceLocation(entityItem.getEntityItem().getItemSpriteNumber());
        val icon = ((TextureMap) texturemanager.getTexture(resourcelocation)).getAtlasSprite("potato");

        GL11.glPushMatrix();
        GL11.glColor4f(1F, 1F, 1F, 1F);

        val minU = icon.getMinU();
        val maxU = icon.getMaxU();
        val minV = icon.getMinV();
        val maxV = icon.getMaxV();

        val itemStack = entityItem.getEntityItem();
        if (RenderManager.instance.options.fancyGraphics) {
            val thickness = 1F / 16F;
            val itemSpacing = thickness * 0.35F;
            val multiItemCount = fancyMultiItemCount(itemStack.stackSize);
            if (itemStack.getItemSpriteNumber() == 0) {
                bindTexture(TextureMap.locationBlocksTexture);
            } else {
                bindTexture(TextureMap.locationItemsTexture);
            }

            if (renderInFrame) {
                GL11.glRotatef(180F, 0F, 1F, 0F);
            } else {
                GL11.glRotatef(entitySpin(entityItem, partialTick()), 0F, 1F, 0F);
            }

            GL11.glTranslatef(-0.5F, -0.25F, -((thickness + itemSpacing) * (multiItemCount / 2F - 1F)));

            for (var i = 0; i < multiItemCount; ++i) {
                if (i > 0 && shouldSpreadItems()) {
                    float x = (random.nextFloat() * 2F - 1F) * 0.3F;
                    float y = (random.nextFloat() * 2F - 1F) * 0.3F;
                    GL11.glTranslatef(x, y, 0.084375F);
                }

                renderer.render();

                //ItemRenderer.renderItemIn2D(Tessellator.instance, maxU, minV, minU, maxV, icon.getIconWidth(), icon.getIconHeight(), thickness);

                if (itemStack.hasEffect(0) || itemStack.hasEffect(1)) {
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
//
                    GL11.glColor4f(1F, 1F, 1F, 1F);
                    if (itemStack.getItemSpriteNumber() == 0) {
                        bindTexture(TextureMap.locationBlocksTexture);
                    } else {
                        bindTexture(TextureMap.locationItemsTexture);
                    }
                }
            }
        } else {
            GL11.glTranslatef(-0.5F, -0.25F, 0F);
            if (!renderInFrame)
                GL11.glRotatef(180.0F - RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
            val miniBlockCount = getMiniBlockCount(itemStack.stackSize);
            for (int i = 0; i < miniBlockCount; ++i) {
                GL11.glPushMatrix();

                if (i > 0) {
                    val xOffset = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
                    val yOffset = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
                    val zOffset = (random.nextFloat() * 2.0F - 1.0F) * 0.3F;
                    GL11.glTranslatef(xOffset, yOffset, zOffset);
                }

                Tessellator.instance.startDrawingQuads();
                Tessellator.instance.setNormal(0.0F, 1.0F, 0.0F);
                Tessellator.instance.addVertexWithUV(0F, 0F, 0.0D, minU, maxV);
                Tessellator.instance.addVertexWithUV(1F, 0F, 0.0D, maxU, maxV);
                Tessellator.instance.addVertexWithUV(1F, 1F, 0.0D, maxU, minV);
                Tessellator.instance.addVertexWithUV(0F, 1F, 0.0D, minU, minV);
                Tessellator.instance.draw();
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }

    protected static float entityBob(@NonNull EntityItem entityItem, float subTick) {
        if (renderInFrame)
            return 0.1F;
        return MathHelper.sin(((float) entityItem.age + subTick) / 10.0F + entityItem.hoverStart) * 0.1F + 0.1F;
    }

    protected static float entitySpin(@NonNull EntityItem entityItem, float subTick) {
        if (renderInFrame)
            return 0;
        return (((float) entityItem.age + subTick) / 20.0F + entityItem.hoverStart) * (180F / (float) Math.PI);
    }

    public int getMiniBlockCount(int stackSize) {
        if (stackSize > 40)
            return 5;
        if (stackSize > 20)
            return 4;
        if (stackSize > 5)
            return 3;
        if (stackSize > 1)
            return 2;
        return 1;
    }

    public int fancyMultiItemCount(int stackSize) {
        if (stackSize < 2)
            return 1;
        if (stackSize < 16)
            return 2;
        if (stackSize < 32)
            return 3;
        return 4;
    }

    public void renderItemIntoGUI(FontRenderer fontRenderer, TextureManager textureManager, ItemStack itemStack, int p_77015_4_, int p_77015_5_) {
        this.renderItemIntoGUI(fontRenderer, textureManager, itemStack, p_77015_4_, p_77015_5_, false);
    }

    public void renderItemIntoGUI(FontRenderer p_77015_1_, TextureManager p_77015_2_, ItemStack p_77015_3_, int p_77015_4_, int p_77015_5_, boolean renderEffect) {
        int k = p_77015_3_.getItemDamage();
        Object object = p_77015_3_.getIconIndex();
        int l;
        float f;
        float f3;
        float f4;

        if (p_77015_3_.getItemSpriteNumber() == 0 && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(p_77015_3_.getItem()).getRenderType())) {
            p_77015_2_.bindTexture(TextureMap.locationBlocksTexture);
            Block block = Block.getBlockFromItem(p_77015_3_.getItem());
            GL11.glEnable(GL11.GL_ALPHA_TEST);

            if (block.getRenderBlockPass() != 0) {
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
                GL11.glEnable(GL11.GL_BLEND);
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            } else {
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
                GL11.glDisable(GL11.GL_BLEND);
            }

            GL11.glPushMatrix();
            GL11.glTranslatef((float) (p_77015_4_ - 2), (float) (p_77015_5_ + 3), -3.0F + this.zLevel);
            GL11.glScalef(10.0F, 10.0F, 10.0F);
            GL11.glTranslatef(1.0F, 0.5F, 1.0F);
            GL11.glScalef(1.0F, 1.0F, -1.0F);
            GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            l = p_77015_3_.getItem().getColorFromItemStack(p_77015_3_, 0);
            f3 = (float) (l >> 16 & 255) / 255.0F;
            f4 = (float) (l >> 8 & 255) / 255.0F;
            f = (float) (l & 255) / 255.0F;

            if (renderWithColor) {
                GL11.glColor4f(f3, f4, f, 1.0F);
            }

            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            renderBlocks.useInventoryTint = renderWithColor;
            renderBlocks.renderBlockAsItem(block, k, 1.0F);
            renderBlocks.useInventoryTint = true;

            if (block.getRenderBlockPass() == 0) {
                GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            }

            GL11.glPopMatrix();
        } else if (p_77015_3_.getItem().requiresMultipleRenderPasses()) {
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            p_77015_2_.bindTexture(TextureMap.locationItemsTexture);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(0, 0, 0, 0);
            GL11.glColorMask(false, false, false, true);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.setColorOpaque_I(-1);
            tessellator.addVertex(p_77015_4_ - 2, p_77015_5_ + 18, zLevel);
            tessellator.addVertex(p_77015_4_ + 18, p_77015_5_ + 18, zLevel);
            tessellator.addVertex(p_77015_4_ + 18, p_77015_5_ - 2, zLevel);
            tessellator.addVertex(p_77015_4_ - 2, p_77015_5_ - 2, zLevel);
            tessellator.draw();
            GL11.glColorMask(true, true, true, true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);

            Item item = p_77015_3_.getItem();
            for (l = 0; l < item.getRenderPasses(k); ++l) {
                OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                p_77015_2_.bindTexture(item.getSpriteNumber() == 0 ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);
                IIcon iicon = item.getIcon(p_77015_3_, l);
                int i1 = p_77015_3_.getItem().getColorFromItemStack(p_77015_3_, l);
                f = (float) (i1 >> 16 & 255) / 255.0F;
                float f1 = (float) (i1 >> 8 & 255) / 255.0F;
                float f2 = (float) (i1 & 255) / 255.0F;

                if (renderWithColor) {
                    GL11.glColor4f(f, f1, f2, 1.0F);
                }

                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_ALPHA_TEST);

                renderIcon(p_77015_4_, p_77015_5_, iicon, 16, 16);

                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_LIGHTING);

                if (renderEffect && p_77015_3_.hasEffect(l)) {
                    renderEffect(p_77015_2_, p_77015_4_, p_77015_5_);
                }
            }

            GL11.glEnable(GL11.GL_LIGHTING);
        } else {
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            ResourceLocation resourcelocation = p_77015_2_.getResourceLocation(p_77015_3_.getItemSpriteNumber());
            p_77015_2_.bindTexture(resourcelocation);

            if (object == null) {
                object = ((TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(resourcelocation)).getAtlasSprite("missingno");
            }

            l = p_77015_3_.getItem().getColorFromItemStack(p_77015_3_, 0);
            f3 = (float) (l >> 16 & 255) / 255.0F;
            f4 = (float) (l >> 8 & 255) / 255.0F;
            f = (float) (l & 255) / 255.0F;

            if (renderWithColor)
                GL11.glColor4f(f3, f4, f, 1.0F);


            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);

            renderIcon(p_77015_4_, p_77015_5_, (IIcon) object, 16, 16);

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glDisable(GL11.GL_BLEND);

            if (renderEffect && p_77015_3_.hasEffect(0)) {
                renderEffect(p_77015_2_, p_77015_4_, p_77015_5_);
            }
            GL11.glEnable(GL11.GL_LIGHTING);
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
    }

    public void renderItemAndEffectIntoGUI(FontRenderer p_82406_1_, TextureManager p_82406_2_, final ItemStack p_82406_3_, int p_82406_4_, int p_82406_5_) {
        if (p_82406_3_ != null) {
            zLevel += 50.0F;

            try {
                if (!ForgeHooksClient.renderInventoryItem(field_147909_c, p_82406_2_, p_82406_3_, renderWithColor, zLevel, (float) p_82406_4_, (float) p_82406_5_))
                    renderItemIntoGUI(p_82406_1_, p_82406_2_, p_82406_3_, p_82406_4_, p_82406_5_, true);
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Rendering item");
                CrashReportCategory crashreportcategory = crashreport.makeCategory("Item being rendered");
                crashreportcategory.addCrashSectionCallable("Item Type", new Callable() {
                    private static final String __OBFID = "CL_00001004";

                    public String call() {
                        return String.valueOf(p_82406_3_.getItem());
                    }
                });
                crashreportcategory.addCrashSectionCallable("Item Aux", new Callable() {
                    private static final String __OBFID = "CL_00001005";

                    public String call() {
                        return String.valueOf(p_82406_3_.getItemDamage());
                    }
                });
                crashreportcategory.addCrashSectionCallable("Item NBT", new Callable() {
                    private static final String __OBFID = "CL_00001006";

                    public String call() {
                        return String.valueOf(p_82406_3_.getTagCompound());
                    }
                });
                crashreportcategory.addCrashSectionCallable("Item Foil", new Callable() {
                    private static final String __OBFID = "CL_00001007";

                    public String call() {
                        return String.valueOf(p_82406_3_.hasEffect());
                    }
                });
                throw new ReportedException(crashreport);
            }

            this.zLevel -= 50.0F;
        }
    }

    public void renderEffect(TextureManager manager, int x, int y) {
        GL11.glDepthFunc(GL11.GL_EQUAL);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        manager.bindTexture(RES_ITEM_GLINT);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(0.5F, 0.25F, 0.8F, 1.0F);
        renderGlint(x - 2, y - 2, 20, 20);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
    }

    private void renderGlint(int p_77018_2_, int p_77018_3_, int p_77018_4_, int p_77018_5_) {
        for (int j1 = 0; j1 < 2; ++j1) {
            OpenGlHelper.glBlendFunc(772, 1, 0, 0);
            float f = 0.00390625F;
            float f1 = 0.00390625F;
            float f2 = (float) (Minecraft.getSystemTime() % (long) (3000 + j1 * 1873)) / (3000.0F + (float) (j1 * 1873)) * 256.0F;
            float f3 = 0.0F;
            Tessellator tessellator = Tessellator.instance;
            float f4 = 4.0F;

            if (j1 == 1) {
                f4 = -1.0F;
            }

            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(p_77018_2_ + 0, p_77018_3_ + p_77018_5_, this.zLevel, (f2 + (float) p_77018_5_ * f4) * f, (f3 + (float) p_77018_5_) * f1);
            tessellator.addVertexWithUV(p_77018_2_ + p_77018_4_, p_77018_3_ + p_77018_5_, this.zLevel, (f2 + (float) p_77018_4_ + (float) p_77018_5_ * f4) * f, (f3 + (float) p_77018_5_) * f1);
            tessellator.addVertexWithUV(p_77018_2_ + p_77018_4_, p_77018_3_ + 0, this.zLevel, (f2 + (float) p_77018_4_) * f, (f3 + 0.0F) * f1);
            tessellator.addVertexWithUV(p_77018_2_ + 0, p_77018_3_ + 0, this.zLevel, (f2 + 0.0F) * f, (f3 + 0.0F) * f1);
            tessellator.draw();
        }
    }

    /**
     * Renders the item's overlay information. Examples being stack count or damage on top of the item's image at the
     * specified position.
     */
    public void renderItemOverlayIntoGUI(FontRenderer p_77021_1_, TextureManager p_77021_2_, ItemStack p_77021_3_, int p_77021_4_, int p_77021_5_) {
        renderItemOverlayIntoGUI(p_77021_1_, p_77021_2_, p_77021_3_, p_77021_4_, p_77021_5_, null);
    }

    public void renderItemOverlayIntoGUI(FontRenderer p_94148_1_, TextureManager p_94148_2_, ItemStack p_94148_3_, int p_94148_4_, int p_94148_5_, String p_94148_6_) {
        if (p_94148_3_ != null) {
            if (p_94148_3_.stackSize > 1 || p_94148_6_ != null) {
                String s1 = p_94148_6_ == null ? String.valueOf(p_94148_3_.stackSize) : p_94148_6_;
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDisable(GL11.GL_BLEND);
                p_94148_1_.drawStringWithShadow(s1, p_94148_4_ + 19 - 2 - p_94148_1_.getStringWidth(s1), p_94148_5_ + 6 + 3, 16777215);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            }

            if (p_94148_3_.getItem().showDurabilityBar(p_94148_3_)) {
                double health = p_94148_3_.getItem().getDurabilityForDisplay(p_94148_3_);
                int j1 = (int) Math.round(13.0D - health * 13.0D);
                int k = (int) Math.round(255.0D - health * 255.0D);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glDisable(GL11.GL_BLEND);
                Tessellator tessellator = Tessellator.instance;
                int l = 255 - k << 16 | k << 8;
                int i1 = (255 - k) / 4 << 16 | 16128;
                renderQuad(p_94148_4_ + 2, p_94148_5_ + 13, 13, 2, 0);
                renderQuad(p_94148_4_ + 2, p_94148_5_ + 13, 12, 1, i1);
                renderQuad(p_94148_4_ + 2, p_94148_5_ + 13, j1, 1, l);
                //GL11.glEnable(GL11.GL_BLEND); // Forge: Disable Bled because it screws with a lot of things down the line.
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    private void renderQuad(int posX, int posY, int width, int height, int color) {
        Tessellator.instance.setColorOpaque_I(color);
        Tessellator.instance.addVertex(posX + 0, posY + 0, 0.0D);
        Tessellator.instance.addVertex(posX + 0, posY + height, 0.0D);
        Tessellator.instance.addVertex(posX + width, posY + height, 0.0D);
        Tessellator.instance.addVertex(posX + width, posY + 0, 0.0D);
        Tessellator.instance.draw();
    }

    public void renderIcon(int p_94149_1_, int p_94149_2_, IIcon p_94149_3_, int p_94149_4_, int p_94149_5_) {
        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.addVertexWithUV(p_94149_1_ + 0, p_94149_2_ + p_94149_5_, zLevel, p_94149_3_.getMinU(), p_94149_3_.getMaxV());
        Tessellator.instance.addVertexWithUV(p_94149_1_ + p_94149_4_, p_94149_2_ + p_94149_5_, zLevel, p_94149_3_.getMaxU(), p_94149_3_.getMaxV());
        Tessellator.instance.addVertexWithUV(p_94149_1_ + p_94149_4_, p_94149_2_ + 0, zLevel, p_94149_3_.getMaxU(), p_94149_3_.getMinV());
        Tessellator.instance.addVertexWithUV(p_94149_1_ + 0, p_94149_2_ + 0, zLevel, p_94149_3_.getMinU(), p_94149_3_.getMinV());
        Tessellator.instance.draw();
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return getEntityTexture((EntityItem) p_110775_1_);
    }

    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        doRender((EntityItem) p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    @Override
    public boolean shouldSpreadItems() {
        return true;
    }

    @Override
    public boolean shouldBob() {
        return true;
    }
}
