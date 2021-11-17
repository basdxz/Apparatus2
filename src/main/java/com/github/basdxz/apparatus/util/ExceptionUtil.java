package com.github.basdxz.apparatus.util;

import com.github.basdxz.apparatus.ApparatusMod;
import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import lombok.experimental.UtilityClass;
import net.minecraft.nbt.NBTTagCompound;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;

@UtilityClass
public class ExceptionUtil {
    public void reportNBTWriteException(IParaTile paraTile, int posX, int posY, int posZ,
                                        NBTTagCompound nbtTag, Exception exception) {
        ApparatusMod.error("A ParaTile has failed to save it's NBT!");
        printNBT(nbtTag);
        printError(paraTile, exception, posX, posY, posZ);
    }

    public void reportNBTReadException(IParaTile paraTile, int posX, int posY, int posZ,
                                       NBTTagCompound nbtTag, Exception exception) {
        ApparatusMod.error("A ParaTile has failed to load it's NBT!");
        printNBT(nbtTag);
        printError(paraTile, exception, posX, posY, posZ);
    }

    private void printNBT(NBTTagCompound nbtTag) {
        ApparatusMod.error("Full NBT Tag:%s ", nbtTag.toString());
    }

    public void reportTileEntityUpdateException(IParaTile paraTile, int posX, int posY, int posZ, Exception exception) {
        ApparatusMod.error("A ParaTile has failed to tick!");
        printError(paraTile, exception, posX, posY, posZ);
    }

    private void printError(IParaTile paraTile, Exception exception, int posX, int posY, int posZ) {
        ApparatusMod.error(listParaTileInfo(paraTile));
        ApparatusMod.error(listLocationInfo(posX, posY, posZ));
        ApparatusMod.error(getStackTrace(exception));
    }

    private String listParaTileInfo(IParaTile paraTile) {
        return String.format("Mod ID:%s Para Tile Manager:%s Tile ID:%s",
                paraTile.manager().modid(), paraTile.manager().name(), paraTile.tileID());
    }

    private String listLocationInfo(int posX, int posY, int posZ) {
        return String.format("PosX:%d PosY:%d PosZ:%d", posX, posY, posZ);
    }
}
