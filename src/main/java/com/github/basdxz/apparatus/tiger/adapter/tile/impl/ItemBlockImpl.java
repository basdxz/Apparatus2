package com.github.basdxz.apparatus.tiger.adapter.tile.impl;

import com.github.basdxz.apparatus.tiger.adapter.tile.IBlockImpl;
import com.github.basdxz.apparatus.tiger.adapter.tile.ITileAdapter;
import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockImpl extends ItemBlock {
    protected final ITileAdapter tileAdapter;

    public ItemBlockImpl(Block block) {
        super(block);
        ensureValidBlock(block);
        tileAdapter = ((IBlockImpl) block).tileAdapter();
    }

    protected void ensureValidBlock(@NonNull Block block) {
        if (!(block instanceof IBlockImpl))
            throw new IllegalArgumentException("Block must implement IBlockImpl"); //TODO: Better exceptions
    }
}
