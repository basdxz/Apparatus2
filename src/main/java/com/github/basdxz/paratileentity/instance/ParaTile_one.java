package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.ParaTileEntityMod;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

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
    public boolean singleton() {
        return false;
    }
}
