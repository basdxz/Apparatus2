package com.github.basdxz.paratileentity.defenition.tile.proxy;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

/*
   List all Block functions and make a separate list of ones that don't have [World, posX, posY, posZ]
 */
public interface IParaBlockProxy {
    void onBlockPlacedBy(EntityLivingBase entityLivingBase, ItemStack itemStack);

    void onPostBlockPlaced();

    void registerBlockIcons(IIconRegister iconRegister);

    IIcon getIcon(ForgeDirection side);

    void breakBlock();

    ArrayList<ItemStack> getDrops(int fortune);

    void onBlockClicked(EntityPlayer entityPlayer);

    boolean onBlockActivated(EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ);

    void updateBlock();
}
