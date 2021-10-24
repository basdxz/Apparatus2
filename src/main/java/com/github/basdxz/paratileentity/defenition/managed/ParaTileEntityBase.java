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

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@Getter
@Accessors(fluent = true)
public abstract class ParaTileEntityBase extends TileEntity implements IParaTileEntity {
    private IParaTile paraTile;

    @Override
    public IParaTileEntity registerTileEntity(String name) {
        GameRegistry.registerTileEntity(getClass(), MODID + ":" + name + "_ParaTileEntity");
        return this;
    }

    @Override
    public void tileID(int tileID) {
        if (IParaTileManager.tileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        blockMetadata = tileID;
    }

    @Override
    public int tileID() {
        if (IParaTileManager.tileIDInvalid(blockMetadata))
            blockMetadata = 0;
        return blockMetadata;
    }

    @Override
    public TileEntity newTileEntity(World world, int tileID) {
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
    public boolean clientSide() {
        return worldObj.isRemote;
    }

    @Override
    public boolean serverSide() {
        return !worldObj.isRemote;
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
        nbtTagCompound.setInteger("tileID", blockMetadata);

        if (!paraTile.singleton())
            System.out.println("pog");
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        blockMetadata = nbtTagCompound.getInteger("tileID");

        initParaTile();
        if (!paraTile.singleton())
            System.out.println("pog 2");
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
        paraTile = manager().paraTile(tileID());
        if (paraTile.singleton())
            return;
        paraTile = paraTile.clone();
    }
}
