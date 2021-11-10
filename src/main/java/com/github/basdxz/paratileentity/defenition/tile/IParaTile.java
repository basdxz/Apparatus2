package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;

public interface IParaTile extends Cloneable, IProxiedBlock, IProxiedItemBlock, IProxiedTileEntity {
    void init(IParaTileManager manager);

    void register(IParaTileManager manager);

    IParaTile clone();
}
