package com.github.basdxz.paratileentity;

import com.github.basdxz.paratileentity.defenition.ParaTile;
import com.github.basdxz.paratileentity.defenition.ParaTileManager;
import com.github.basdxz.paratileentity.defenition.proxied.ParaTileEntity;
import lombok.val;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = ParaTileEntityMod.MODID, name = ParaTileEntityMod.NAME, version = ParaTileEntityMod.VERSION)
public class ParaTileEntityMod {
    public static final String MODID = "paratileentity";
    public static final String NAME = "ParaTileEntity";
    public static final String VERSION = "@GRADLE_VERSION_TOKEN@";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ParaTileEntity.register();

        val wolliWoo = new ParaTileManager("test");

        wolliWoo.registerTile(ParaTile.class, 1);
    }
}
