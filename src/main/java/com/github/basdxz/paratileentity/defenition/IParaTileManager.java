package com.github.basdxz.paratileentity.defenition;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import net.minecraft.item.ItemBlock;

public interface IParaTileManager {
    int MAX_TILE_ID = Short.MAX_VALUE;

    static boolean tileIDInvalid(int id) {
        return id < 0 || id > MAX_TILE_ID;
    }

    String name();

    IParaTileEntity paraTileEntity();

    IParaBlock paraBlock();

    Class<? extends ItemBlock> itemClass();

    void registerTile(IParaTile tile);

    IParaTile paraTile(int id);

    Iterable<IParaTile> tileList();

    Iterable<Integer> allTileIDs();
}
