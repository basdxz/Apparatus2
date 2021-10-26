package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;

public interface IParaTile extends Cloneable, IProxiedBlock, IProxiedItemBlock, IProxiedTileEntity {
    IParaTile clone();

    void init(IParaTileManager manager);
}
