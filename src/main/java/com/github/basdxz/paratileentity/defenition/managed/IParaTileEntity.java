package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

// TODO implement 'isTileValid' type function that checks the tile id
public interface IParaTileEntity extends IParaManaged {
    String TILE_ENTITY_ID_POSTFIX = "ParaTileEntity";
    int DEFAULT_TILE_ENTITY_PACKET_FLAG = 0;

    String PARA_TILE_ID_INT_NBT_TAG = "para_tile_id";

    IParaTileEntity registerTileEntity(String modid, String name);

    void reloadTileEntity(String newTileID);

    void loadParaTile(IParaTile paraTile);

    default void writeTileIDToNBT(NBTTagCompound nbtTag) {
        nbtTag.setString(PARA_TILE_ID_INT_NBT_TAG, tileID());
    }

    default String readTileIDFromNBT(NBTTagCompound nbtTag) {
        return nbtTag.getString(PARA_TILE_ID_INT_NBT_TAG);
    }

    default IProxiedTileEntity proxiedTileEntity() {
        return paraTile();
    }

    IParaTile paraTile();

    default String tileID() {
        return paraTile().tileID();
    }

    TileEntity tileEntity();

    TileEntity createNewTileEntity();

    IParaTileEntity worldObj(World worldObj);

    World worldObj();

    IParaTileEntity posX(int posX);

    int posX();

    IParaTileEntity posY(int posY);

    int posY();

    IParaTileEntity posZ(int posZ);

    int posZ();
}
