package com.github.basdxz.paratileentity.defenition.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

/*
   Copy across "Proxied" functions into their own interface.
   List all Block functions and make a separate list of ones that don't have [World, posX, posY, posZ]
   Implement and pass through the functions with defaults provided here.
 */
public interface IProxiedBlock extends IProxiedComponent {
    default void onBlockPlacedBy(EntityLivingBase entityLivingBase, ItemStack itemStack) {
    }

    default void onPostBlockPlaced() {
    }

    default void registerBlockIcons(IIconRegister iconRegister) {
    }

    default IIcon getIcon(ForgeDirection side) {
        return getMissingIcon();
    }

    static IIcon getMissingIcon() {
        return ((TextureMap) Minecraft
                .getMinecraft()
                .getTextureManager()
                .getTexture(TextureMap.locationBlocksTexture))
                .getAtlasSprite("missingno");
    }

    default ArrayList<ItemStack> getDrops(int fortune) {
        ArrayList<ItemStack> itemDropList = new ArrayList<>();
        itemDropList.add(newItemStack());
        return itemDropList;
    }

    default void onBlockClicked(World world, int posX, int posY, int posZ, EntityPlayer entityPlayer) {
    }

    default boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        return false;
    }

    default void updateBlock() {
        worldObj().markBlockForUpdate(posX(), posY(), posZ());
    }
}
