package com.github.basdxz.apparatus.util;

import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import lombok.experimental.UtilityClass;
import net.minecraft.nbt.NBTTagCompound;

import static com.github.basdxz.apparatus.util.LoggingUtil.error;

@UtilityClass
public class ExceptionUtil {

    public void reportNBTWriteException(IParaTile paraTile, int posX, int posY, int posZ,
                                        NBTTagCompound nbtTag, Exception exception) {
        error("A ParaTile has failed to save it's NBT.\n" +
                nbtTag + "\n" +
                tileInfo(paraTile, posX, posY, posZ), exception);
    }

    public void reportNBTReadException(IParaTile paraTile, int posX, int posY, int posZ,
                                       NBTTagCompound nbtTag, Exception exception) {
        error("A ParaTile has failed to load it's NBT.\n" +
                nbtTag + "\n" +
                tileInfo(paraTile, posX, posY, posZ), exception);
    }

    public void reportTileEntityUpdateException(IParaTile paraTile, int posX, int posY, int posZ, Exception exception) {
        error("A ParaTile has failed to tick.\n" +
                tileInfo(paraTile, posX, posY, posZ), exception);
    }

    private String tileInfo(IParaTile paraTile, int posX, int posY, int posZ) {
        return listParaTileInfo(paraTile) + "\n" + listLocationInfo(posX, posY, posZ);
    }

    private String listParaTileInfo(IParaTile paraTile) {
        return String.format("Mod ID:%s Para Tile Manager:%s Tile ID:%s",
                paraTile.manager().modid(), paraTile.manager().name(), paraTile.tileID());
    }

    private String listLocationInfo(int posX, int posY, int posZ) {
        return String.format("PosX:%d PosY:%d PosZ:%d", posX, posY, posZ);
    }

}
