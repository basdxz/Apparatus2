package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.chisel.IChiselRendering;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.client.render.SubmapMultiManager;
import team.chisel.ctmlib.ISubmapManager;

@SuperBuilder
public class ChiselTextureTest extends ParaTile implements IChiselRendering {
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        registerChiselBlockIcons();
    }

    @Override
    public ISubmapManager submapManager() {
        return SubmapMultiManager.ofGlowCTM("futura/screenMetallic");
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return getChiselIcon();
    }
}
