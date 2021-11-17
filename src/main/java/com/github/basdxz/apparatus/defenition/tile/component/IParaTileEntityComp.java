package com.github.basdxz.apparatus.defenition.tile.component;

import com.github.basdxz.apparatus.defenition.managed.IParaTileEntity;
import com.github.basdxz.apparatus.defenition.tile.proxy.IParaTileEntityProxy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public interface IParaTileEntityComp extends IParaTileComp, IParaTileEntityProxy {
    @Override
    default boolean canUpdate() {
        return false;
    }

    @Override
    default void updateEntity() {
    }

    @Override
    default void writeToNBT(NBTTagCompound nbtTagCompound) {
    }

    @Override
    default void readFromNBT(NBTTagCompound nbtTagCompound) {
    }

    // TODO invert logic and find a better name
    default boolean unCloneable() {
        return true;
    }

    IParaTileEntityComp tileEntity(IParaTileEntity tileEntity);

    // TODO explain effect of not being cloneable
    IParaTileEntity tileEntity();

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
