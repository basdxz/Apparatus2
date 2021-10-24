package com.github.basdxz.paratileentity;

import com.github.basdxz.paratileentity.instance.ObamaCasing;
import com.github.basdxz.paratileentity.instance.ParaTileEntity;
import com.github.basdxz.paratileentity.instance.ParaTile_two;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import static com.github.basdxz.paratileentity.instance.ParaTileEntity.MANAGER;

@Mod(modid = ParaTileEntityMod.MODID, name = ParaTileEntityMod.NAME, version = ParaTileEntityMod.VERSION)
public class ParaTileEntityMod {
    public static final String MODID = "paratileentity";
    public static final String NAME = "ParaTileEntity";
    public static final String VERSION = "@GRADLE_VERSION_TOKEN@";

    public static final int BLOCK_UPDATE_FLAG = 1;
    public static final int SEND_TO_CLIENT_FLAG = 2;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ParaTileEntity.registerParaTileEntity();
        MANAGER.registerTile(ParaTile_two.builder().tileID(0).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(1).casingID(0).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(2).casingID(1).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(3).casingID(2).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(4).casingID(3).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(5).casingID(4).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(6).casingID(5).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(7).casingID(6).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(8).casingID(7).build());
    }
}
