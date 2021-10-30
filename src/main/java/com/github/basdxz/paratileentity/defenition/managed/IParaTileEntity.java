package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedTileEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IParaTileEntity extends IParaManaged {
    String TILE_ENTITY_ID_POST_FIX = "ParaTileEntity";
    int DEFAULT_TILE_ENTITY_PACKET_FLAG = 0;

    String TILE_ENTITY_ID_STR_NBT_TAG = "id";
    String TILE_ENTITY_X_POS_INT_NBT_TAG = "x";
    String TILE_ENTITY_Y_POS_INT_NBT_TAG = "y";
    String TILE_ENTITY_Z_POS_INT_NBT_TAG = "z";
    String TILE_ID_INT_NBT_TAG = "tileID";

    IParaTileEntity registerTileEntity(String modid, String name);

    static boolean isNBTFromParaTileEntity(NBTTagCompound nbtTagCompound) {
        return nbtTagCompound.getString(TILE_ENTITY_ID_STR_NBT_TAG)
                .contains(TILE_ENTITY_ID_POST_FIX);
    }

    default void writeTileIDToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger(TILE_ID_INT_NBT_TAG, tileID());
    }

    default IProxiedTileEntity proxiedTileEntity() {
        return paraTile();
    }

    IParaTile paraTile();

    default int tileID() {
        return paraTile().tileID();
    }

    TileEntity createNewTileEntity();

    @Deprecated //TODO Replace with more robust worldObj loading.
    default TileEntitySide side() {
        return TileEntitySide.side(worldObj());
    }

    World worldObj();

    int posX();

    int posY();

    int posZ();

    @Deprecated //TODO Replace with more robust worldObj loading.
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
