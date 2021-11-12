package com.github.basdxz.paratileentity;

import com.github.basdxz.paratileentity.instance.ParaTileEntity;
import com.github.basdxz.paratileentity.util.TestItem;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ParaTileEntityMod.MODID, name = ParaTileEntityMod.NAME, version = ParaTileEntityMod.VERSION,
        dependencies = "required-after:spongemixins@[1.1.0,);" + "required-after:chisel;")
public class ParaTileEntityMod {
    public static final String MODID = "paratileentity";
    public static final String NAME = "ParaTileEntity";
    public static final String VERSION = "@GRADLE_VERSION_TOKEN@";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //if (!Utils.isDevelopmentEnvironment())
        //    return;
        ParaTileEntity.load();
        new TestItem();
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
