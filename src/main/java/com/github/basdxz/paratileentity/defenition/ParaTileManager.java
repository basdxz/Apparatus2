package com.github.basdxz.paratileentity.defenition;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.NonNull;
import lombok.val;


public class ParaTileManager {
    public static final int MAX_TILE_ID = Short.MAX_VALUE;
    private final BiMap<ParaTile, Integer> tileIDBiMap = HashBiMap.create();

    public void registerTile(@NonNull Class<? extends ParaTile> tileClass, int id) {
        ParaTile tile;

        try {
            val constructor = tileClass.getDeclaredConstructor();
            tile = constructor.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("ParaTile must declare no args constructor.");
        }

        if (tileIDBiMap.containsKey(tile))
            throw new IllegalArgumentException("Tile already registered.");

        if (!isTileIDValid(id))
            throw new IllegalArgumentException("Tile ID out of bounds.");

        if (tileIDBiMap.containsValue(id))
            throw new IllegalArgumentException("ID already taken.");

        tileIDBiMap.put(tile, id);
    }

    public static boolean isTileIDValid(int id) {
        return id > 0 && id <= MAX_TILE_ID;
    }
}
