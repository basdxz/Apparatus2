package com.github.basdxz.paratileentity.defenition;

import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import lombok.*;
import net.minecraft.item.ItemBlock;

public interface IParaTileManager {
    int MAX_TILE_ID = Short.MAX_VALUE;

    static boolean isTileIDInvalid(int id) {
        return id < 0 || id > MAX_TILE_ID;
    }

    String getName();

    IParaTileEntity getTileEntity();

    Class<? extends ItemBlock> getItemClass();

    void registerTile(IParaTile tile);

    IParaTile getTile(int id);

    Iterable<Integer> allTileIDs();
}
