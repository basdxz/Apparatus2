package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.TileEntitySide;

public interface IProxiedTileEntity {
    IProxiedTileEntity tileEntity(IParaTileEntity tileEntity);

    IParaTileEntity tileEntity();

    boolean singleton();

    boolean canUpdate();

    void updateEntity();

    void writeToNBT(NBTTagCompound nbtTagCompound);

    void readFromNBT(NBTTagCompound nbtTagCompound);

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
