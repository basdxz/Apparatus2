package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@Getter
@Accessors(fluent = true)
public abstract class ParaTileEntityBase extends TileEntity implements IParaTileEntity {
    private IParaTile paraTile;

    @Override
    public IParaTileEntity registerTileEntity(String modid, String name) {
        GameRegistry.registerTileEntity(getClass(), modid + ":" + name + "_ParaTileEntity");
        return this;
    }

    @Override
    public IParaTileEntity tileID(int tileID) {
        if (IParaTileManager.tileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        blockMetadata = tileID;
        return this;
    }

    @Override
    public int tileID() {
        if (IParaTileManager.tileIDInvalid(blockMetadata))
            blockMetadata = 0;
        return blockMetadata;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int tileID) {
        try {
            val paraTileEntity = getClass().getDeclaredConstructor().newInstance();
            paraTileEntity.setWorldObj(world);
            paraTileEntity.tileID(tileID);
            return paraTileEntity;
        } catch (Exception e) {
            throw new IllegalStateException("Class extending IParaTileEntity didn't implement a no argument constructor.");
        }
    }

    @Override
    public World worldObj() {
        return worldObj;
    }

    @Override
    public int posX() {
        return xCoord;
    }

    @Override
    public int posY() {
        return yCoord;
    }

    @Override
    public int posZ() {
        return zCoord;
    }

    @Override
    public void updateEntity() {
        proxiedTileEntity().updateEntity();
    }

    @Override
    public boolean canUpdate() {
        initParaTile();
        return proxiedTileEntity().canUpdate();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        writeTileIDToNBT(nbtTagCompound);
        if (!paraTile.singleton())
            paraTile.writeToNBT(nbtTagCompound);
    }


    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        readTileIDFromToNBT(nbtTagCompound);
        initParaTile();
        if (!paraTile.singleton())
            paraTile.readFromNBT(nbtTagCompound);
    }

    @Override
    public Packet getDescriptionPacket() {
        val nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, DEFAULT_TILE_ENTITY_PACKET_FLAG, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        readFromNBT(packet.func_148857_g());
    }

    protected void initParaTile() {
        if (paraTile != null)
            return;
        val baseParaTile = manager().paraTile(tileID());
        if (baseParaTile.singleton()) {
            paraTile = baseParaTile;
        } else {
            /*
                Allows the clone() method to have access to this TileEntity as well as the cloned class
                But prevents leaving a reference to **this** TileEntity, preventing unexpected use.
             */
            baseParaTile.tileEntity(this);
            paraTile = baseParaTile.clone();
            baseParaTile.tileEntity(null);
        }
    }
}
