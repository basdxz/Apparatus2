package com.github.basdxz.paratileentity.defenition.chisel;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import net.minecraft.util.IIcon;
import team.chisel.ctmlib.ISubmapManager;

public interface IChiselRendering {
    ISubmapManager submapManager();

    default void registerChiselBlockIcons() {
        manager().carvingHelper().addVariation(tileID(), submapManager());
    }

    /*
        Makes the breaking particles correct.
     */
    default IIcon getChiselIcon() {
        return manager().carvingHelper().getIcon(0, tileID());
    }

    int tileID();

    IParaTileManager manager();
}
