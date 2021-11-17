package com.github.basdxz.apparatus;

import com.github.basdxz.apparatus.instance.ParaTileEntity;
import com.github.basdxz.apparatus.util.TestItem;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ApparatusMod.MODID, name = ApparatusMod.NAME, version = ApparatusMod.VERSION,
        dependencies = "required-after:spongemixins@[1.1.0,);" + "required-after:chisel;")
public class ApparatusMod {
    public static final String MODID = "apparatus";
    public static final String NAME = "Apparatus";
    public static final String VERSION = "@GRADLE_VERSION_TOKEN@";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ParaTileEntity.init();
        new TestItem();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ParaTileEntity.postInit();
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
