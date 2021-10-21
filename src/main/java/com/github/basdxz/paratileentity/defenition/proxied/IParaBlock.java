package com.github.basdxz.paratileentity.defenition.proxied;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;

public interface IParaBlock extends ITileEntityProvider {
    IParaTileManager getManager();

    Block getBlock();
}
