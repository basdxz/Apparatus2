package com.github.basdxz.paratileentity.defenition.proxied;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ParaItemBlock extends ItemBlock implements IParaItemBlock {
    public ParaItemBlock(Block block) {
        super(block);
    }
}
