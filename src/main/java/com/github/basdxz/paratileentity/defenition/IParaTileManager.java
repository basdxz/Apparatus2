package com.github.basdxz.paratileentity.defenition;

import com.github.basdxz.paratileentity.defenition.chisel.CarvableHelperExtended;
import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IParaTileManager {
    int MAX_TILE_ID = Short.MAX_VALUE;

    static boolean tileIDInvalid(int id) {
        return id < 0 || id > MAX_TILE_ID;
    }

    String name();

    String modid();

    CarvableHelperExtended carvingHelper();

    IParaBlock paraBlock();

    Class<? extends ItemBlock> itemClass();

    TileEntity createNewTileEntity(World world, int tileID);

    IParaTile registerTile(IParaTile tile);

    IParaTile paraTile(int id);

    Iterable<IParaTile> tileList();

    Iterable<Integer> allTileIDs();

    void bufferTile(IParaTile tile);

    IParaTile bufferTile();
}
