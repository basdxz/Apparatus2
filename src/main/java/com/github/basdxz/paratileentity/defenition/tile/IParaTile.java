package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;

public interface IParaTile extends Cloneable, IProxiedBlock, IProxiedItemBlock, IProxiedTileEntity {
    IParaTile manager(IParaTileManager manager);

    IParaTile clone();
}
