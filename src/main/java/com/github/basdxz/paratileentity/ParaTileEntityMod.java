package com.github.basdxz.paratileentity;

import com.github.basdxz.paratileentity.defenition.ParaTileManager;
import com.github.basdxz.paratileentity.instance.ObamaCasing;
import com.github.basdxz.paratileentity.instance.ParaTile_one;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import lombok.val;

@Mod(modid = ParaTileEntityMod.MODID, name = ParaTileEntityMod.NAME, version = ParaTileEntityMod.VERSION)
public class ParaTileEntityMod {
    public static final String MODID = "paratileentity";
    public static final String NAME = "ParaTileEntity";
    public static final String VERSION = "@GRADLE_VERSION_TOKEN@";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        val manager = new ParaTileManager("test_tile");
        manager.registerTile(ParaTile_one.builder().tileID(0).build());

        manager.registerTile(ObamaCasing.builder().tileID(1).casingID(0).build());
        manager.registerTile(ObamaCasing.builder().tileID(2).casingID(1).build());
        manager.registerTile(ObamaCasing.builder().tileID(3).casingID(2).build());
        manager.registerTile(ObamaCasing.builder().tileID(4).casingID(3).build());
        manager.registerTile(ObamaCasing.builder().tileID(5).casingID(4).build());
        manager.registerTile(ObamaCasing.builder().tileID(6).casingID(5).build());
        manager.registerTile(ObamaCasing.builder().tileID(7).casingID(6).build());
        manager.registerTile(ObamaCasing.builder().tileID(8).casingID(7).build());
    }
}
