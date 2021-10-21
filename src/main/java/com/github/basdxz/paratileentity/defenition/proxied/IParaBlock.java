package com.github.basdxz.paratileentity.defenition.proxied;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;

public interface IParaBlock extends ITileEntityProvider {
    Block getBlock();
}
