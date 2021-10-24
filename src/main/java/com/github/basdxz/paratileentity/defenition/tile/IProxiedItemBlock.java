package com.github.basdxz.paratileentity.defenition.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public interface IProxiedItemBlock extends IProxiedComponent {
    int BLOCK_UPDATE_FLAG = 1;
    int SEND_TO_CLIENT_FLAG = 2;

    default String getUnlocalizedName(ItemStack itemStack) {
        return manager().paraBlock().getUnlocalizedName();
    }

    default void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced) {
    }

    default boolean placeBlockAt(ItemStack itemStack, EntityPlayer entityPlayer, World world,
                                 int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (!world.setBlock(posX, posY, posZ, manager().paraBlock().block(), tileID(),
                BLOCK_UPDATE_FLAG | SEND_TO_CLIENT_FLAG)) {
            return false;
        }
        if (world.getBlock(posX, posY, posZ) == manager().paraBlock()) {
            manager().paraBlock().block().onBlockPlacedBy(world, posX, posY, posZ, entityPlayer, itemStack);
            manager().paraBlock().block().onPostBlockPlaced(world, posX, posY, posZ, tileID());
        }
        return true;
    }
}
