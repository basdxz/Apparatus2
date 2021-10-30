package com.github.basdxz.paratileentity;

import com.github.basdxz.paratileentity.instance.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import static com.github.basdxz.paratileentity.instance.ParaTileEntity.MANAGER;

@Mod(modid = ParaTileEntityMod.MODID, name = ParaTileEntityMod.NAME, version = ParaTileEntityMod.VERSION,
        dependencies = "required-after:chisel;" +
                "required-after:spongemixins@[1.1.0,);")
public class ParaTileEntityMod {
    public static final String MODID = "paratileentity";
    public static final String NAME = "ParaTileEntity";
    public static final String VERSION = "@GRADLE_VERSION_TOKEN@";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ParaTileEntity.load();
        MANAGER.registerTile(ObamaCasing.builder().tileID(1).casingID(0).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(2).casingID(1).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(3).casingID(2).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(4).casingID(3).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(5).casingID(4).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(6).casingID(5).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(7).casingID(6).build());
        MANAGER.registerTile(ObamaCasing.builder().tileID(8).casingID(7).build());
        MANAGER.registerTile(TurbinePart.builder().tileID(9).maxDurability(500).maxSpeed(20).build());
        MANAGER.registerTile(SidedExample.builder().tileID(10).build());
        MANAGER.registerTile(ClickableBlockTest.builder().tileID(11).build());
        //MANAGER.registerTile(ObamaCasing.builder().tileID(12).casingID(0).build());

        MANAGER.registerTile(ClickableBlockTest.builder().tileID(220).build());
    }
}
