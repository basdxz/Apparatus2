package com.github.basdxz.apparatus.defenition.chisel;

import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.shadow.team.chisel.ctmlib.ISubmapManager;

public interface IChiselRendering extends IParaTile {
    @SideOnly(Side.CLIENT)
    IChiselRendering submapManager(ISubmapManager submapManager);

    @SideOnly(Side.CLIENT)
    ISubmapManager submapManager();

    @SideOnly(Side.CLIENT)
    ISubmapManager newSubmapManager();

    @SideOnly(Side.CLIENT)
    default void registerChiselBlockIcons() {
        submapManager(newSubmapManager());
        manager().carvingHelper().addVariation(tileID(), submapManager());
    }

    @SideOnly(Side.CLIENT)
    default IIcon getChiselIcon(ForgeDirection side) {
        return manager().carvingHelper().getIcon(tileID(), side.ordinal());
    }
}
