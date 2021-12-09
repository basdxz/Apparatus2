package com.github.basdxz.apparatus.defenition.tile.component;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.tile.proxy.IParaBlockProxy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.val;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

import static net.minecraft.client.Minecraft.getMinecraft;

/*
   Implement and pass through the functions with defaults provided here.
 */
public interface IParaBlockComp extends IParaTileComp, IParaBlockProxy {
    @Override
    default void onBlockPlacedBy(EntityLivingBase entityLivingBase, ItemStack itemStack) {
    }

    @Override
    default void onPostBlockPlaced() {
    }

    @Override
    @SideOnly(Side.CLIENT)
    default void registerBlockIcons(IIconRegister iconRegister) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    default IIcon getIcon(ForgeDirection side) {
        return missingIcon();
    }

    @Override
    default int getRenderBlockPass() {
        return 0;
    }

    @Override
    default boolean canRenderInPass(int pass) {
        return getRenderBlockPass() == pass;
    }

    @Override
    default boolean isOpaqueCube() {
        return true;
    }

    //TODO: This will not work nicely with non-full blocks (Stairs, buttons etc..)
    @Override
    default boolean shouldSideBeRendered(int posX, int posY, int posZ, int side) {
        val block = getMinecraft().theWorld.getBlock(posX, posY, posZ);
        if (!(block instanceof IParaBlock))
            return !block.isOpaqueCube();

        val otherTile = ((IParaBlock) block).paraTile(getMinecraft().theWorld, posX, posY, posZ);
        if (otherTile.isOpaqueCube())
            return false;

        return !(tileID().equals(otherTile.tileID()) && manager().equals(otherTile.manager()));
    }

    @Override
    default int getLightOpacity() {
        return isOpaqueCube() ? 255 : 0;
    }

    @Override
    default float getAmbientOcclusionLightValue() {
        return isOpaqueCube() ? 0.2F : 1.0F;
    }

    @Override
    default boolean isBlockNormalCube() {
        return isOpaqueCube();
    }

    @SideOnly(Side.CLIENT)
    static IIcon missingIcon() {
        return ((TextureMap) getMinecraft()
                .getTextureManager()
                .getTexture(TextureMap.locationBlocksTexture))
                .getAtlasSprite("missingno");
    }

    @Override
    default void breakBlock() {
    }

    @Override
    default ArrayList<ItemStack> getDrops(int fortune) {
        ArrayList<ItemStack> itemDropList = new ArrayList<>();
        itemDropList.add(newItemStack());
        return itemDropList;
    }

    @Override
    default void onBlockClicked(EntityPlayer entityPlayer) {
    }

    @Override
    default boolean onBlockActivated(EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    default void updateBlock() {
        worldObj().markBlockForUpdate(posX(), posY(), posZ());
    }
}
