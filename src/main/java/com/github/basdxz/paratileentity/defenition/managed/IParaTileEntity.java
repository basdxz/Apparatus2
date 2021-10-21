package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedTileEntity;
import net.minecraft.tileentity.TileEntity;

public interface IParaTileEntity extends IParaManaged {
    int DEFAULT_TILE_ENTITY_PACKET_FLAG = 0;

    default IProxiedTileEntity getProxiedTileEntity() {
        return getTile();
    }

    default IParaTile getTile() {
        return getManager().getTile(getTileID());
    }

    void setTileID(int tileID);

    int getTileID();

    TileEntity createNewTileEntity();

    boolean isClientSide();

    boolean isServerSide();
}
