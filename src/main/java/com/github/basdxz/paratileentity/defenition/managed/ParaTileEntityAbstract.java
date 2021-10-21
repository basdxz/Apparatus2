package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

@Getter
@Accessors(fluent = true)
public abstract class ParaTileEntityAbstract extends TileEntity implements IParaTileEntity {
    private final IParaTileManager manager;

    protected ParaTileEntityAbstract(IParaTileManager manager) {
        this.manager = manager;
    }

    @Override
    public void tileID(int tileID) {
        if (IParaTileManager.tileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        blockMetadata = tileID;
    }

    @Override
    public int tileID() {
        return IParaTileManager.tileIDInvalid(blockMetadata) ? 0 : blockMetadata;
    }

    @Override
    public IParaTile paraTile() {
        return manager.paraTile(tileID());
    }

    @Override
    public boolean clientSide() {
        if (!hasWorldObj())
            throw new IllegalStateException("Can't check without valid world object.");
        return worldObj.isRemote;
    }

    @Override
    public boolean serverSide() {
        if (!hasWorldObj())
            throw new IllegalStateException("Can't check without valid world object.");
        return !worldObj.isRemote;
    }

    @Override
    public void updateEntity() {
        proxiedTileEntity().updateEntity();
    }

    @Override
    public boolean canUpdate() {
        return proxiedTileEntity().canUpdate();
    }

    @Override
    public void readFromNBT(NBTTagCompound aNBT) {
        super.readFromNBT(aNBT);
        blockMetadata = aNBT.getInteger("tileID");
    }

    @Override
    public void writeToNBT(NBTTagCompound aNBT) {
        aNBT.setInteger("tileID", blockMetadata);
        super.writeToNBT(aNBT);
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
}
