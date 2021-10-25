package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.managed.CarvableHelperExtended;
import net.minecraft.util.IIcon;
import team.chisel.ctmlib.ISubmapManager;

public interface IChiselRendering {
    ISubmapManager submapManager();

    default void registerChiselBlockIcons() {
        CarvableHelperExtended.INSTANCE.addVariation(tileID(), submapManager());
    }

    /*
        Makes the breaking particles correct.
     */
    default IIcon getChiselIcon() {
        return CarvableHelperExtended.INSTANCE.getIcon(0, tileID());
    }

    int tileID();
}
