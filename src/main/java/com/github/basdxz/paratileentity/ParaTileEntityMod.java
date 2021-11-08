package com.github.basdxz.paratileentity;

import com.github.basdxz.paratileentity.instance.*;
import com.github.basdxz.paratileentity.util.Utils;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.github.basdxz.paratileentity.instance.ParaTileEntity.MANAGER;

@Mod(modid = ParaTileEntityMod.MODID, name = ParaTileEntityMod.NAME, version = ParaTileEntityMod.VERSION,
        dependencies = "required-after:spongemixins@[1.1.0,);" + "required-after:chisel;" + "required-after:NotEnoughItems;")
public class ParaTileEntityMod {
    public static final String MODID = "paratileentity";
    public static final String NAME = "ParaTileEntity";
    public static final String VERSION = "@GRADLE_VERSION_TOKEN@";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (!Utils.isDevelopmentEnvironment())
            return;
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
        MANAGER.registerTile(ObamaCasing.builder().tileID(12).casingID(0).build());
        MANAGER.registerTile(ClickableBlockTest.builder().tileID(220).build());
        MANAGER.registerTile(ClickableBlockTest.builder().tileID(Short.MAX_VALUE).build());
    }

    public static void debug(String message, Object... params) {
        LOGGER.debug(message, params);
    }

    public static void info(String message, Object... params) {
        LOGGER.info(message, params);
    }

    public static void warn(String message, Object... params) {
        LOGGER.warn(message, params);
    }

    public static void error(String message, Object... params) {
        LOGGER.error(message, params);
    }
}
