package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface IProxiedTileEntity {
    void tileEntity(IParaTileEntity tileEntity);

    IParaTileEntity tileEntity();

    boolean singleton();

    boolean canUpdate();

    void updateEntity();

    void writeToNBT(NBTTagCompound nbtTagCompound);

    void readFromNBT(NBTTagCompound nbtTagCompound);

    default boolean clientSide() {
        return worldObj().isRemote;
    }

    default boolean serverSide() {
        if (worldObj() == null) {
            System.out.println("wogi");
            return true;
        }
        return !worldObj().isRemote;
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
