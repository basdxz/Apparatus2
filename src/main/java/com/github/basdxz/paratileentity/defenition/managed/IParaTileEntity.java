package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IParaTileEntity extends IParaManaged {
    int DEFAULT_TILE_ENTITY_PACKET_FLAG = 0;
    String TILE_ID_NBT_TAG = "tileID";

    IParaTileEntity registerTileEntity(String name);

    default IProxiedTileEntity proxiedTileEntity() {
        return paraTile();
    }

    default IParaTile paraTile() {
        return manager().paraTile(tileID());
    }

    default void writeTileIDToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger(TILE_ID_NBT_TAG, tileID());
    }

    default void readTileIDFromToNBT(NBTTagCompound nbtTagCompound) {
        tileID(nbtTagCompound.getInteger(TILE_ID_NBT_TAG));
    }

    IParaTileEntity tileID(int tileID);

    int tileID();

    TileEntity createNewTileEntity(World world, int tileID);

    default TileEntitySide side() {
        return TileEntitySide.side(worldObj());
    }

    World worldObj();

    int posX();

    int posY();

    int posZ();

    enum TileEntitySide {
        UNKNOWN, CLIENT, SERVER;

        public static TileEntitySide side(World world) {
            if (world == null)
                return UNKNOWN;
            if (world.isRemote)
                return CLIENT;
            return SERVER;
        }
    }
}
