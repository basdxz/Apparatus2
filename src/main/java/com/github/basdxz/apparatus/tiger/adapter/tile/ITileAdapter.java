package com.github.basdxz.apparatus.tiger.adapter.tile;

import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.common.entity.ITile;
import com.github.basdxz.apparatus.tiger.adapter.item.IItemAdapter;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public interface ITileAdapter extends IItemAdapter {
    @Override
    default IItem item() {
        return tile();
    }

    @Override
    default Item minecraftItem() {
        return itemBlock();
    }

    ITile tile();

    Block block();

    ItemBlock itemBlock();
}
