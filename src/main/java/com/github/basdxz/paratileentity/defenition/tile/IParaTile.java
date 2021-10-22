package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;

public interface IParaTile extends Cloneable, IProxiedBlock, IProxiedItemBlock, IProxiedTileEntity {
    int tileID();

    IParaTileManager manager();

    void registerManager(IParaTileManager manager);

    IParaTile clone();

    boolean singleton();
}
