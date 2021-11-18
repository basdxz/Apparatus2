package com.github.basdxz.apparatus.defenition.managed;

import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import com.github.basdxz.apparatus.defenition.tile.proxy.IParaTileEntityProxy;
import lombok.val;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import static com.github.basdxz.apparatus.defenition.IParaTileManager.NULL_TILE_ID;

public interface IParaTileEntity extends IParaManaged {
    String TILE_ENTITY_ID_POSTFIX = "ParaTileEntity";
    int DEFAULT_TILE_ENTITY_PACKET_FLAG = 0;

    String PARA_TILE_ID_INT_NBT_TAG = "para_tile_id";

    IParaTileEntity registerTileEntity(String modid, String name);

    default void writeTileIDToNBT(NBTTagCompound nbtTag) {
        nbtTag.setString(PARA_TILE_ID_INT_NBT_TAG, tileID());
    }

    default void readTileIDFromNBT(NBTTagCompound nbtTag) {
        val tileID = nbtTag.getString(PARA_TILE_ID_INT_NBT_TAG);
        expectedTileID((tileID != null && !tileID.equals("")) ? tileID : NULL_TILE_ID);
    }

    IParaTileEntity expectedTileID(String expectedTileID);

    default IParaTileEntityProxy proxiedTileEntity() {
        return paraTile();
    }

    void reloadParaTile();

    IParaTileEntity paraTile(IParaTile paraTile);

    IParaTile paraTile();

    default String tileID() {
        return paraTile().tileID();
    }

    TileEntity tileEntity();

    TileEntity newTileEntity();

    IParaTileEntity world(World worldObj);

    World world();

    IParaTileEntity posX(int posX);

    int posX();

    IParaTileEntity posY(int posY);

    int posY();

    IParaTileEntity posZ(int posZ);

    int posZ();
}
