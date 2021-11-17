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

    default boolean cloneable() {
        return true;
    }

    IParaTileEntityComp tileEntity(IParaTileEntity tileEntity);

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
