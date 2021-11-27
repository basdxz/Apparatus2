package com.github.basdxz.apparatus.defenition.tile.proxy;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

/*
   TODO: List all Block functions.
 */
public interface IParaBlockProxy {
    default IParaBlockProxy proxyBlock() {
        return this;
    }

    void onBlockPlacedBy(EntityLivingBase entityLivingBase, ItemStack itemStack);

    void onPostBlockPlaced();

    void registerBlockIcons(IIconRegister iconRegister);

    IIcon getIcon(ForgeDirection side);

    int getRenderBlockPass();

    boolean canRenderInPass(int pass);

    boolean isOpaqueCube();

    boolean shouldSideBeRendered(int posX, int posY, int posZ, int side);

    int getLightOpacity();

    float getAmbientOcclusionLightValue();

    boolean isBlockNormalCube();

    void breakBlock();

    ArrayList<ItemStack> getDrops(int fortune);

    void onBlockClicked(EntityPlayer entityPlayer);

    boolean onBlockActivated(EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ);

    void updateBlock();
}
