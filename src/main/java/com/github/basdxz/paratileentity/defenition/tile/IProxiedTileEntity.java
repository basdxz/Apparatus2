package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/*
   TODO: Copy across "Proxied" functions into their own interface.
   TODO: List all TileEntity functions then Implement and pass through the functions with defaults provided here.
 */
public interface IProxiedTileEntity extends IProxiedComponent {
    IProxiedTileEntity tileEntity(IParaTileEntity tileEntity);

    IParaTileEntity tileEntity();

    default boolean singleton() {
        return true;
    }

    default boolean canUpdate() {
        return false;
    }

    default void updateEntity() {
    }

    default void writeToNBT(NBTTagCompound nbtTagCompound) {
    }

    default void readFromNBT(NBTTagCompound nbtTagCompound) {
    }

    default boolean serverSide() {
        return !worldObj().isRemote;
    }


    default boolean clientSide() {
        return worldObj().isRemote;
    }

    default World worldObj() {
        return tileEntity().worldObj();
    }

    default int posX() {
        return tileEntity().posX();
    }

    default int posY() {
        return tileEntity().posY();
    }

    default int posZ() {
        return tileEntity().posZ();
    }
}
