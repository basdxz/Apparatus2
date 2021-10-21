package com.github.basdxz.paratileentity.defenition;

import com.github.basdxz.paratileentity.defenition.managed.*;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import lombok.NonNull;
import net.minecraft.item.ItemBlock;

public class ParaTileManager implements IParaTileManager {
    @Getter
    private final Class<? extends ItemBlock> itemClass = ParaItemBlock.class;

    protected final BiMap<IParaTile, Integer> tileIDBiMap = HashBiMap.create();
    @Getter
    protected final String name;
    protected final IParaBlock block;
    @Getter
    protected final IParaTileEntity tileEntity;

    public ParaTileManager(String name) {
        this.name = name;
        block = new ParaBlock(this);
        tileEntity = new ParaTileEntity(this);
    }

    @Override
    public void registerTile(IParaTile tile) {
        if (tileIDBiMap.containsKey(tile))
            throw new IllegalArgumentException("Tile already registered.");

        if (IParaTileManager.isTileIDInvalid(tile.tileID()))
            throw new IllegalArgumentException("Tile ID out of bounds.");

        if (tileIDBiMap.containsValue(tile.tileID()))
            throw new IllegalArgumentException("ID already taken.");

        tileIDBiMap.put(tile, tile.tileID());
        tile.registerManager(this);
    }

    @Override
    public IParaTile getTile(int id) {
        if (IParaTileManager.isTileIDInvalid(id))
            throw new IllegalArgumentException("Tile ID out of bounds.");

        if (!tileIDBiMap.containsValue(id))
            throw new IllegalArgumentException("Tile ID doesn't exist.");

        return tileIDBiMap.inverse().get(id);
    }

    @Override
    public Iterable<Integer> allTileIDs() {
        return tileIDBiMap.values();
    }
}
