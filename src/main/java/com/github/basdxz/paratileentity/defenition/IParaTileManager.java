package com.github.basdxz.paratileentity.defenition;

import com.github.basdxz.paratileentity.defenition.chisel.CarvableHelperExtended;
import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.tile.BufferedParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IBufferedParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IParaTileManager {
    int MAX_TILE_ID = Short.MAX_VALUE;
    int NULL_TILE_ID = 0;

    static boolean tileIDInvalid(int id) {
        return id < 0 || id > MAX_TILE_ID;
    }

    String name();

    String modid();

    void postInit();

    CarvableHelperExtended carvingHelper();

    default Block block() {
        return paraBlock().block();
    }

    IParaBlock paraBlock();

    Class<? extends ItemBlock> itemClass();

    TileEntity createNewTileEntity();

    IParaTile registerTile(IParaTile tile);

    IParaTile paraTile(int id);

    Iterable<IParaTile> tileList();

    Iterable<Integer> allTileIDs();

    IBufferedParaTile nullTile();

    default void bufferedTile(World world, int posX, int posY, int posZ, int tileID) {
        bufferedTile(new BufferedParaTile(world, posX, posY, posZ, paraTile(tileID)));
    }

    default void bufferedTile(IParaTile paraTile) {
        bufferedTile(new BufferedParaTile(paraTile));
    }

    void bufferedTile(IBufferedParaTile bufferedTile);

    IBufferedParaTile bufferedTile();

    boolean bufferedTileNull();
}
