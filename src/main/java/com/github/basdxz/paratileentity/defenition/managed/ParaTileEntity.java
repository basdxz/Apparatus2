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
public abstract class ParaTileEntity extends TileEntity implements IParaTileEntity {
    private final Class<? extends ParaTileEntity> baseClass;
    private final IParaTileManager manager;

    private IParaTile paraTile;

    protected ParaTileEntity() {
        this.baseClass = registerBaseClass();
        this.manager = registerManager();
    }

    protected abstract Class<? extends ParaTileEntity> registerBaseClass();

    protected abstract IParaTileManager registerManager();

    public void registerTileEntity() {
        GameRegistry.registerTileEntity(baseClass, MODID + ":" + manager.name() + "_ParaTileEntity");
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
            val paraTileEntity = new ParaTileEntity() {
                @Override
                protected Class<? extends ParaTileEntity> registerBaseClass() {
                    return baseClass;
                }
                @Override
                protected IParaTileManager registerManager() {
                    return manager;
                }
            };
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

    protected void initParaTile() {
        if (paraTile != null)
            return;
        paraTile = manager.paraTile(tileID());
        if (paraTile.singleton())
            return;
        paraTile = paraTile.clone();
    }

    @Override
    public void writeToNBT(NBTTagCompound aNBT) {
        String tileEntityName = (String) jailbreak().classToNameMap.get(baseClass);

        if (tileEntityName == null)
            throw new RuntimeException(baseClass + " is missing a mapping! This is a bug!");

        aNBT.setString("id", tileEntityName);
        aNBT.setInteger("x", this.xCoord);
        aNBT.setInteger("y", this.yCoord);
        aNBT.setInteger("z", this.zCoord);
        aNBT.setInteger("tileID", blockMetadata);
    }

    @Override
    public void readFromNBT(NBTTagCompound aNBT) {
        xCoord = aNBT.getInteger("x");
        yCoord = aNBT.getInteger("y");
        zCoord = aNBT.getInteger("z");
        blockMetadata = aNBT.getInteger("tileID");
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
