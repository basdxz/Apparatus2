package com.github.basdxz.apparatus.adapter.tile.impl;

import com.github.basdxz.apparatus.adapter.render.IItemRendererImpl;
import com.github.basdxz.apparatus.adapter.render.impl.ItemRendererImpl;
import com.github.basdxz.apparatus.adapter.tile.ITileAdapter;
import com.github.basdxz.apparatus.common.entity.ITile;
import lombok.*;
import lombok.experimental.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

@Getter
@Accessors(fluent = true, chain = true)
public class TileAdapter implements ITileAdapter {
    protected final ITile tile;
    protected final Block block;
    protected final ItemBlock itemBlock;
    protected final IItemRendererImpl itemRenderer; //TODO: Custom block renderer

    public TileAdapter(@NonNull ITile tile) {
        this.tile = tile;
        block = newBlock();
        itemBlock = getItemBlock();
        itemRenderer = newItemRenderer();
    }

    protected Block newBlock() {
        return new BlockImpl(this, ItemBlockImpl.class);
    }

    protected ItemBlock getItemBlock() {
        return (ItemBlock) Item.getItemFromBlock(block);
    }

    protected IItemRendererImpl newItemRenderer() {
        return new ItemRendererImpl(this);
    }
}
