package com.github.basdxz.apparatus.defenition.tile;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import com.github.basdxz.apparatus.defenition.tile.component.IParaBlockComp;
import com.github.basdxz.apparatus.defenition.tile.component.IParaItemBlockComp;
import com.github.basdxz.apparatus.defenition.tile.component.IParaTileEntityComp;
import net.minecraft.block.Block;

public interface IParaTile extends Cloneable, IParaBlockComp, IParaItemBlockComp, IParaTileEntityComp {
    @Override
    default IParaTile paraTile() {
        return this;
    }

    void init(IParaTileManager manager);

    void register(IParaTileManager manager);

    void registerRecipes();

    IParaTile clone();

    default Block block() {
        return manager().block();   //TODO: does this belong?
    }
}
