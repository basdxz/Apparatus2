package com.github.basdxz.apparatus.defenition;

import com.github.basdxz.apparatus.defenition.chisel.CarvableHelperExtended;
import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.tile.BufferedParaTile;
import com.github.basdxz.apparatus.defenition.tile.IBufferedParaTile;
import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IParaTileManager {
    String NULL_TILE_ID = "NULL";

    // FIXME: FLAT_FIX (delete?)
    static boolean tileIDInvalid(String id) {
        return false;
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

    IParaTile paraTile(String id);

    Iterable<IParaTile> tileList();

    Iterable<String> allTileIDs();

    IBufferedParaTile nullTile();

    default void bufferedTile(World world, int posX, int posY, int posZ, String tileID) {
        bufferedTile(new BufferedParaTile(world, posX, posY, posZ, paraTile(tileID)));
    }

    default void bufferedTile(IParaTile paraTile) {
        bufferedTile(new BufferedParaTile(paraTile));
    }

    void bufferedTile(IBufferedParaTile bufferedTile);

    IBufferedParaTile bufferedTile();

    boolean bufferedTileNull();
}
