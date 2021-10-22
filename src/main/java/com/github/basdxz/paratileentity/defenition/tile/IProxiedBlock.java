package com.github.basdxz.paratileentity.defenition.tile;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public interface IProxiedBlock {
    void registerBlockIcons(IIconRegister iconRegister);

    IIcon getIcon(ForgeDirection side);
}
