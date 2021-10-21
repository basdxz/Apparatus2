package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.val;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

@NoArgsConstructor // Needed for Server-Side TE init
public class ParaTileEntity extends TileEntity implements IParaTileEntity {
    static {
        GameRegistry.registerTileEntity(ParaTileEntity.class, "ParaTileEntity");
    }

    @Getter
    protected IParaTileManager manager;

    public ParaTileEntity(IParaTileManager manager) {
        this.manager = manager;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        getProxiedTileEntity().updateEntity();
    }

    @Override
    public boolean canUpdate() {
        registerManager(); // Included here as it is run after the no args constructor is
        return getProxiedTileEntity().canUpdate();
    }

    protected void registerManager() {
        if (manager != null)
            return;
        val block = getBlockType();
        if (block == null)
            throw new IllegalStateException("Block was null on update, this means I made a mistake.");
        if (!(block instanceof IParaBlock))
            throw new IllegalStateException("Bound block hasn't implemented IParaBlock");
        manager = ((IParaBlock) block).getManager();
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

    @Override
    public TileEntity createNewTileEntity() {
        return new ParaTileEntity(manager);
    }

    @Override
    public void setTileID(int tileID) {
        if (IParaTileManager.isTileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        blockMetadata = tileID;
    }

    @Override
    public int getTileID() {
        return IParaTileManager.isTileIDInvalid(blockMetadata) ? 0 : blockMetadata;
    }

    @Override
    public IParaTile getTile() {
        return manager.getTile(getTileID());
    }

    @Override
    public boolean isClientSide() {
        return worldObj.isRemote;
    }

    @Override
    public boolean isServerSide() {
        return !worldObj.isRemote;
    }
}
