package com.github.basdxz.apparatus.defenition.chisel;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.ctmlib.ISubmapManager;

public interface IChiselRendering {
    ISubmapManager submapManager();

    default void registerChiselBlockIcons() {
        manager().carvingHelper().addVariation(tileID(), submapManager());
    }

    default IIcon getChiselIcon(ForgeDirection side) {
        return manager().carvingHelper().getIcon(tileID(), side.ordinal());
    }

    String tileID();

    IParaTileManager manager();
}
