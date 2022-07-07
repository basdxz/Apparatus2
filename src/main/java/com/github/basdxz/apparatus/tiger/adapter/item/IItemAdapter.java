package com.github.basdxz.apparatus.tiger.adapter.item;

import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.tiger.adapter.render.IItemRendererImpl;
import net.minecraft.item.Item;

public interface IItemAdapter {
    IItem item();

    Item minecraftItem();

    IItemRendererImpl itemRenderer();
}
