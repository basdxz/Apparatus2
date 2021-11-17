package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.tile.component.IParaBlockComp;
import com.github.basdxz.paratileentity.defenition.tile.component.IParaItemBlockComp;
import com.github.basdxz.paratileentity.defenition.tile.component.IParaTileEntityComp;

public interface IParaTile extends Cloneable, IParaBlockComp, IParaItemBlockComp, IParaTileEntityComp {
    void init(IParaTileManager manager);

    void register(IParaTileManager manager);

    void registerRecipes();

    IParaTile clone();
}
