package com.github.basdxz.apparatus.defenition.tile.component;

import com.github.basdxz.apparatus.defenition.tile.proxy.IParaBlockProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

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
    default void registerBlockIcons(IIconRegister iconRegister) {
    }

    @Override
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

    static IIcon missingIcon() {
        return ((TextureMap) Minecraft
                .getMinecraft()
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
