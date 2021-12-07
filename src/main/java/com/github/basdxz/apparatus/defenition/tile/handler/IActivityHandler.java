package com.github.basdxz.apparatus.defenition.tile.handler;

import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import net.minecraft.nbt.NBTTagCompound;

public interface IActivityHandler extends IParaTile {
    String ACTIVITY_NBT_TAG = "active";

    IActivityHandler active(boolean active);

    boolean active();

    default void updateActivity(boolean active) {
        if (active() != active) {
            active(active);
            updateBlock();
        }
    }

    default void writeActivityToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setBoolean(ACTIVITY_NBT_TAG, active());
    }

    default void readActivityFromToNBT(NBTTagCompound nbtTagCompound) {
        active(nbtTagCompound.getBoolean(ACTIVITY_NBT_TAG));
    }
}
