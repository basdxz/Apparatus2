package com.github.basdxz.paratileentity;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = ParaTileEntityMod.MODID, name = ParaTileEntityMod.NAME, version = ParaTileEntityMod.VERSION)
public class ParaTileEntityMod {
    public static final String MODID = "examplemod";
    public static final String NAME = "Example Mod";
    public static final String VERSION = "@version@";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        //Some example code
        System.out.println("DIRT BLOCK 2 >> " + Blocks.dirt.getUnlocalizedName());

        System.out.println(MODID);
        System.out.println(MODID);
        System.out.println(MODID);
    }
}
