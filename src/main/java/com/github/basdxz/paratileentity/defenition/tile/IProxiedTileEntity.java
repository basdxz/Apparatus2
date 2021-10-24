package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.TileEntitySide;

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

    default TileEntitySide side() {
        return tileEntity().side();
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
