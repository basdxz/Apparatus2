package com.github.basdxz.paratileentity.defenition.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/*
   TODO: Copy across "Proxied" functions into their own interface.
   TODO: List all ItemBlock functions then Implement and pass through the functions with defaults provided here.
 */
public interface IProxiedItemBlock extends IProxiedComponent {
    int BLOCK_UPDATE_FLAG = 1;
    int SEND_TO_CLIENT_FLAG = 2;

    default String getUnlocalizedName(ItemStack itemStack) {
        return "tile." + manager().modid() + "." + manager().name();
    }

    default void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced) {
    }

    // FIXME: FLAT_FIX
    default boolean placeBlockAt(ItemStack itemStack, EntityPlayer entityPlayer, World world,
                                 int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        //if (!world.setBlock(posX, posY, posZ, manager().block(), tileID(),
        //        BLOCK_UPDATE_FLAG | SEND_TO_CLIENT_FLAG)) {
        //    return false;
        //}
        //if (world.getBlock(posX, posY, posZ) == manager().block()) {
        //    manager().block().onBlockPlacedBy(world, posX, posY, posZ, entityPlayer, itemStack);
        //    manager().block().onPostBlockPlaced(world, posX, posY, posZ, tileID());
        //}
        if (!world.setBlock(posX, posY, posZ, manager().block(), 0,
                BLOCK_UPDATE_FLAG | SEND_TO_CLIENT_FLAG)) {
            return false;
        }
        if (world.getBlock(posX, posY, posZ) == manager().block()) {
            manager().block().onBlockPlacedBy(world, posX, posY, posZ, entityPlayer, itemStack);
            manager().block().onPostBlockPlaced(world, posX, posY, posZ, 0);
        }
        return true;
    }

    // FIXME: FLAT_FIX
    default ItemStack newItemStack(int count) {
        //return new ItemStack(manager().block(), count, tileID());
        return new ItemStack(manager().block(), count, 0);
    }
}
