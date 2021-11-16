package com.github.basdxz.paratileentity.defenition.chisel;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import net.minecraft.util.IIcon;
import team.chisel.ctmlib.ISubmapManager;

public interface IChiselRendering {
    ISubmapManager submapManager();

    // FIXME: FLAT_FIX
    default void registerChiselBlockIcons() {
        //manager().carvingHelper().addVariation(tileID(), submapManager());
        manager().carvingHelper().addVariation(0, submapManager());
    }

    /*
        Makes the breaking particles correct.
     */
    // FIXME: FLAT_FIX
    default IIcon getChiselIcon() {
        //return manager().carvingHelper().getIcon(0, tileID());
        return manager().carvingHelper().getIcon(0, 0);
    }

    String tileID();

    IParaTileManager manager();
}
