package com.github.basdxz.paratileentity.defenition;

import com.github.basdxz.paratileentity.defenition.proxied.*;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;
import lombok.NonNull;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ParaTileManager implements IParaTileManager {
    @Getter
    private final Class<? extends ItemBlock> itemClass = ParaItemBlock.class;

    protected final BiMap<ParaTile, Integer> tileIDBiMap = HashBiMap.create();
    @Getter
    protected final String name;
    protected final IParaBlock block;
    protected final IParaTileEntity tileEntity;

    public ParaTileManager(String name) {
        this.name = name;
        block = new ParaBlock(this);
        tileEntity = new ParaTileEntity(this);
    }

    @Override
    public void registerTile(@NonNull Class<? extends ParaTile> tileClass, int id) {
        ParaTile tile;

        try {
            tile = tileClass.getDeclaredConstructor(IParaTileManager.class).newInstance(this);
        } catch (Exception e) {
            throw new IllegalArgumentException("ParaTile must have ParaTile(IParaTileManager manager) constructor.");
        }

        if (tileIDBiMap.containsKey(tile))
            throw new IllegalArgumentException("Tile already registered.");

        if (!IParaTileManager.isTileIDValid(id))
            throw new IllegalArgumentException("Tile ID out of bounds.");

        if (tileIDBiMap.containsValue(id))
            throw new IllegalArgumentException("ID already taken.");

        tileIDBiMap.put(tile, id);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return tileEntity.createNewTileEntity();
    }
}
