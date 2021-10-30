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

import java.lang.reflect.InvocationTargetException;

/*
    TODO: Setup and configure a TileEntitySpecialRenderer dedicated for rendering custom stuff on frame.
 */
@Getter
@Accessors(fluent = true)
public abstract class ParaTileEntityBase extends TileEntity implements IParaTileEntity {
    protected final IParaTile paraTile;

    public ParaTileEntityBase() {
        paraTile = init();
    }

    protected IParaTile init() {
        return safeClone(manager().bufferTile());
    }

    /*
        Only clones non-singleton ParaTiles.

        Also allows the clone() method to have access to this TileEntity as well as the cloned class
        But prevents leaving a reference to **this** TileEntity, preventing unexpected use.
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
    public IParaTileEntity registerTileEntity(String modid, String name) {
        GameRegistry.registerTileEntity(getClass(), modid + ":" + name + "_" + TILE_ENTITY_ID_POST_FIX);
        return this;
    }

    //fixme ID load nonsense fix
    @Override
    public IParaTileEntity tileID(int tileID) {
        if (IParaTileManager.tileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        blockMetadata = tileID;
        return this;
    }

    //fixme ID load nonsense fix (Basically tileID and world should be 100% ignored. We'll load them better later.
    @Override
    public TileEntity createNewTileEntity() {
        try {
            return getClass().getDeclaredConstructor().newInstance();
            //} catch () {
            //throw new IllegalStateException("Class extending IParaTileEntity didn't implement a no argument constructor.");
        } catch (InvocationTargetException e) {
            e.getCause().printStackTrace();
            throw new IllegalStateException("Class extending IParaTileEntity didn't implement a no argument constructor.");
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new IllegalStateException("Class extending IParaTileEntity didn't implement a no argument constructor.");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalStateException("Class extending IParaTileEntity didn't implement a no argument constructor.");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new IllegalStateException("Class extending IParaTileEntity didn't implement a no argument constructor.");
        }
    }

    @Override
    public World worldObj() {
        return worldObj;
    }

    //fixme add pos set
    @Override
    public int posX() {
        return xCoord;
    }

    //fixme add pos set
    @Override
    public int posY() {
        return yCoord;
    }

    //fixme add pos set
    @Override
    public int posZ() {
        return zCoord;
    }

    //fixme add pos set
    @Override
    public void updateEntity() {
        proxiedTileEntity().updateEntity();
    }

    @Override
    public boolean canUpdate() {
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
}
