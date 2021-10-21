package com.github.basdxz.paratileentity.defenition;

import lombok.*;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemBlock;

public interface IParaTileManager extends ITileEntityProvider {
    int MAX_TILE_ID = Short.MAX_VALUE;
    static boolean isTileIDValid(int id) {
        return id > 0 && id <= MAX_TILE_ID;
    }

    String getName();

    Class<? extends ItemBlock> getItemClass();

    void registerTile(@NonNull Class<? extends ParaTile> tileClass, int id);
}
