package com.github.basdxz.paratileentity;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = ParaTileEntityMod.MODID, name = ParaTileEntityMod.NAME, version = ParaTileEntityMod.VERSION)
public class ParaTileEntityMod {
    public static final String MODID = "paratileentity";
    public static final String NAME = "Many In One";
    public static final String VERSION = "@GRADLE_VERSION_TOKEN@";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //Some example code
        System.out.println("DIRT BLOCK 2 >> " + Blocks.dirt.getUnlocalizedName());

        System.out.println(MODID);
        System.out.println(MODID);
        System.out.println(MODID);
    }
}
