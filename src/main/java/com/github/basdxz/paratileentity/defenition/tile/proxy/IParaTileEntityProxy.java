package com.github.basdxz.paratileentity.defenition.tile.proxy;

import net.minecraft.nbt.NBTTagCompound;

public interface IParaTileEntityProxy {
    boolean canUpdate();

    void updateEntity();

    void writeToNBT(NBTTagCompound nbtTagCompound);

    void readFromNBT(NBTTagCompound nbtTagCompound);
}
