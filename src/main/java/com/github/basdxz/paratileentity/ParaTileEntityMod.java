package com.github.basdxz.paratileentity;

import com.github.basdxz.paratileentity.instance.ParaTile_one;
import com.github.basdxz.paratileentity.defenition.ParaTileManager;
import com.github.basdxz.paratileentity.instance.ParaTile_two;
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
        manager.registerTile(ParaTile_one.builder().tileID(0).build());
        manager.registerTile(ParaTile_two.builder().tileID(1).tier(0).build());
        manager.registerTile(ParaTile_two.builder().tileID(2).tier(1).build());
    }
}
