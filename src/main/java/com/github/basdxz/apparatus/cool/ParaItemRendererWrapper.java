package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.parathing.ParaItemRender;
import cpw.mods.fml.relauncher.ReflectionHelper;
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
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.Timer;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.*;

import static com.github.basdxz.apparatus.common.popoga.impl.RenderView.*;
import static net.minecraft.client.Minecraft.getMinecraft;

@SideOnly(Side.CLIENT)
@RequiredArgsConstructor
public class ParaItemRendererWrapper implements IItemRenderer {
    protected final static float ITEM_UNIT_THICKNESS = 0.0625F;
    protected final static Timer MINECRAFT_TIMER = getMinecraftTimer();

    protected static IIcon NULL_TEXTURE_REFERENCE;

    private final boolean renderWithColor = false;
    private final TempRenderItem renderItem = new TempRenderItem();
    @NonNull
    protected final ParaItemRender render;

    {
        renderItem.setRenderManager(RenderManager.instance);
    }

    @SneakyThrows
    protected static Timer getMinecraftTimer() {
        val timerField = ReflectionHelper.findField(Minecraft.class, "timer", "field_71428_T");
        timerField.setAccessible(true);
        return (Timer) timerField.get(getMinecraft());
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
        val ent = new EntityItem(getMinecraft().theWorld, 0F, 0F, 0F, new ItemStack(Blocks.brick_block));
        ent.age = entityItem.age;
        ent.hoverStart = entityItem.hoverStart;
        renderItem.doRender(ent, 0, 0, 0, 0, getSubTick());
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
        GL11.glPushMatrix();
        renderFlat(nullTexture());
        GL11.glPopMatrix();
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
        GL11.glScalef(16F, 16F, 1F);
        Tessellator.instance.addVertexWithUV(0F, 1F, 0F, icon.getMinU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(1F, 1F, 0F, icon.getMaxU(), icon.getMaxV());
        Tessellator.instance.addVertexWithUV(1F, 0F, 0F, icon.getMaxU(), icon.getMinV());
        Tessellator.instance.addVertexWithUV(0F, 0F, 0F, icon.getMinU(), icon.getMinV());
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

    protected static float getSubTick() {
        return MINECRAFT_TIMER.renderPartialTicks;
    }
}
