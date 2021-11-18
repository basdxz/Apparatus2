package com.github.basdxz.apparatus.defenition.chisel;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.ctmlib.ISubmapManager;

public interface IChiselRendering {
    IChiselRendering submapManager(ISubmapManager submapManager);

    ISubmapManager submapManager();

    ISubmapManager newSubmapManager();

    default void registerChiselBlockIcons() {
        submapManager(newSubmapManager());
        manager().carvingHelper().addVariation(tileID(), submapManager());
    }

    default IIcon getChiselIcon(ForgeDirection side) {
        return manager().carvingHelper().getIcon(tileID(), side.ordinal());
    }

    String tileID();

    IParaTileManager manager();
}
