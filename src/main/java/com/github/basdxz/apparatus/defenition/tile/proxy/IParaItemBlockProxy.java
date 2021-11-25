package com.github.basdxz.apparatus.defenition.tile.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/*
   TODO: List all ItemBlock functions.
 */
public interface IParaItemBlockProxy {
    default IParaItemBlockProxy proxiedItemBlock() {
        return this;
    }

    String getUnlocalizedName(ItemStack itemStack);

    void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced);

    boolean placeBlockAt(ItemStack itemStack, EntityPlayer entityPlayer, World world,
                         int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ);
}
