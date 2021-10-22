package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IParaTileEntity extends IParaManaged {
    int DEFAULT_TILE_ENTITY_PACKET_FLAG = 0;

    default IProxiedTileEntity proxiedTileEntity() {
        return paraTile();
    }

    default IParaTile paraTile() {
        return manager().paraTile(tileID());
    }

    void tileID(int tileID);

    int tileID();

    TileEntity newTileEntity(World world, int tileID);

    boolean clientSide();

    boolean serverSide();
}
