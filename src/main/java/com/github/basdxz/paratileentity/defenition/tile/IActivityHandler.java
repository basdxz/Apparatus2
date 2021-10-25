package com.github.basdxz.paratileentity.defenition.tile;

import net.minecraft.nbt.NBTTagCompound;

public interface IActivityHandler {
    String ACTIVITY_NBT_TAG = "active";

    IActivityHandler active(boolean active);

    boolean active();

    default void updateActivity(boolean active) {
        active(active);
        updateBlock();
    }

    void updateBlock();

    default void writeActivityToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setBoolean(ACTIVITY_NBT_TAG, active());
    }

    default void readActivityFromToNBT(NBTTagCompound nbtTagCompound) {
        active(nbtTagCompound.getBoolean(ACTIVITY_NBT_TAG));
    }
}
