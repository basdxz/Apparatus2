package com.github.basdxz.apparatus.instance;

import com.github.basdxz.apparatus.ParaTileEntityMod;
import com.github.basdxz.apparatus.defenition.tile.ParaTile;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.ctmlib.ISubmapManager;

import static com.github.basdxz.apparatus.ParaTileEntityMod.MODID;

@SuperBuilder
public class ParaTile_one extends ParaTile {
    private static IIcon icon;

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        icon = iconRegister.registerIcon(MODID + ":test_tile");
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return icon;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        ParaTileEntityMod.debug("HELLOW from ParaTile!!");
    }

    @Override
    public boolean cloneable() {
        return false;
    }

    @Override
    public ISubmapManager submapManager() {
        return null;
    }
}
