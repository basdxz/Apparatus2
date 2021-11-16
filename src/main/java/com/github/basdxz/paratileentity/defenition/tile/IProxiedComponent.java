package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import lombok.val;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IProxiedComponent {
    int BLOCK_UPDATE_FLAG = 1;
    int SEND_TO_CLIENT_FLAG = 2;

    String tileID();

    IParaTileManager manager();

    World worldObj();

    int posX();

    int posY();

    int posZ();

    default ItemStack newItemStack() {
        return newItemStack(1);
    }

    ItemStack newItemStack(int count);

    default boolean placeInWorld(World world, int posX, int posY, int posZ) {
        if (!world.setBlock(posX, posY, posZ, manager().block(), 0,
                BLOCK_UPDATE_FLAG | SEND_TO_CLIENT_FLAG))
            return false;

        if (world.getBlock(posX, posY, posZ) == manager().block()) {
            return loadParaTile(world, posX, posY, posZ);
        } else {
            return false;
        }
    }

    default boolean loadParaTile(World world, int posX, int posY, int posZ) {
        val tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof IParaTileEntity) {
            ((IParaTileEntity) tileEntity).expectedTileID(tileID()).reloadTileEntity();
            return true;
        }
        return false;
    }
}
