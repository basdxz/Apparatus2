package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;

/*
    TODO: Setup and configure a TileEntitySpecialRenderer dedicated for rendering custom stuff on frame.
 */
@Accessors(fluent = true)
public abstract class ParaTileEntityBase extends TileEntity implements IParaTileEntity {
    @Getter
    protected IParaTile paraTile;
    protected String tileIDFromNBT;

    public ParaTileEntityBase() {
        blockMetadata = getBlockMetadata();
        blockType = getBlockType();
        paraTile = manager().nullTile().paraTile();
        tileIDFromNBT = paraTile.tileID();
    }

    @Override
    public IParaTileEntity registerTileEntity(String modid, String name) {
        GameRegistry.registerTileEntity(getClass(), modid + ":" + name + "_" + TILE_ENTITY_ID_POSTFIX);
        return this;
    }

    @Override
    public TileEntity tileEntity() {
        return this;
    }

    @Override
    public TileEntity createNewTileEntity() {
        try {
            return getClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new IllegalStateException("Class extending ParaTileEntityBase requires no args constructor.");
        } catch (InvocationTargetException e) {
            e.getCause().printStackTrace();
            throw new IllegalStateException("Something went wrong with creating a new instance, refer to stacktrace.");
        }
    }

    @Override
    public IParaTileEntity worldObj(World worldObj) {
        this.worldObj = worldObj;
        return this;
    }

    @Override
    public World worldObj() {
        return worldObj;
    }

    @Override
    public IParaTileEntity posX(int posX) {
        xCoord = posX;
        return this;
    }

    @Override
    public int posX() {
        return xCoord;
    }

    @Override
    public IParaTileEntity posY(int posY) {
        yCoord = posY;
        return this;
    }

    @Override
    public int posY() {
        return yCoord;
    }

    @Override
    public IParaTileEntity posZ(int posZ) {
        zCoord = posZ;
        return this;
    }

    @Override
    public int posZ() {
        return zCoord;
    }

    @Override
    public void updateEntity() {
        proxiedTileEntity().updateEntity();
        validateParaTile();
    }

    protected void validateParaTile() {
        if (paraTile().tileID().equals(tileIDFromNBT))
            return;
        reloadTileEntity(tileIDFromNBT);
    }

    @Override
    public void reloadTileEntity(String newTileID) {
        paraTile = manager().paraTile(newTileID);

        val newParaTileEntity = (IParaTileEntity) createNewTileEntity();
        newParaTileEntity.posX(posX());
        newParaTileEntity.posY(posY());
        newParaTileEntity.posZ(posZ());
        newParaTileEntity.worldObj(worldObj());
        newParaTileEntity.loadParaTile(paraTile);

        worldObj().removeTileEntity(posX(), posY(), posZ());
        worldObj().setTileEntity(posX(), posY(), posZ(), newParaTileEntity.tileEntity());
    }

    @Override
    public void loadParaTile(IParaTile paraTile) {
        tileIDFromNBT = paraTile.tileID();
        this.paraTile = safeClone(paraTile);
        markDirty();
        worldObj().markBlockForUpdate(posX(), posY(), posZ());
    }

    /*
        Only clones non-singleton ParaTiles.

        Also allows the clone() method to have access to this TileEntity as well as the cloned class
        But clears reference to **this** TileEntity, preventing unexpected use.
    */
    protected IParaTile safeClone(IParaTile paraTile) {
        if (paraTile.singleton())
            return paraTile;

        paraTile.tileEntity(this);
        val clonedParaTile = paraTile.clone();
        paraTile.tileEntity(null);
        return clonedParaTile;
    }

    @Override
    public boolean canUpdate() {
        return proxiedTileEntity().canUpdate();
    }

    // TODO: Passthrough to Paratile
    //@Override
    //public void markDirty() {
    //    super.markDirty();
    //}

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
        tileIDFromNBT = readTileIDFromNBT(nbtTagCompound); //Have some check for null?
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

    @Override
    public void updateContainingBlockInfo() {
    }

    @Override
    public int getBlockMetadata() {
        return 0;
    }

    @Override
    public Block getBlockType() {
        return manager().block();
    }
}
