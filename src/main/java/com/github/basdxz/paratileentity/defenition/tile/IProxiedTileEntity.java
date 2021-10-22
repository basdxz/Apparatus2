package com.github.basdxz.paratileentity.defenition.tile;

public interface IProxiedTileEntity {
    boolean singleton();

    boolean canUpdate();

    void updateEntity();
}
