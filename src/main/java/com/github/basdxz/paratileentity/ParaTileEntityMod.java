package com.github.basdxz.paratileentity;

import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import com.github.basdxz.paratileentity.defenition.ParaTileManager;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile_two;
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
        val manager = new ParaTileManager("ACoolParaTile");
        manager.registerTile(ParaTile.class, 0);
        manager.registerTile(ParaTile_two.class, 1);
    }
}
