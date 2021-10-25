package com.github.basdxz.paratileentity.defenition.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

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

    default ItemStack newItemStack() {
        return new ItemStack(manager().paraBlock().block(), 1, tileID());
    }
}
