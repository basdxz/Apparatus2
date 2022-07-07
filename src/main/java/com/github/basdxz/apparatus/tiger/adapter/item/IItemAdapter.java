package com.github.basdxz.apparatus.tiger.adapter.item;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.tiger.adapter.IEntityAdapter;
import com.github.basdxz.apparatus.tiger.adapter.render.IItemRendererImpl;
import net.minecraft.item.Item;

public interface IItemAdapter extends IEntityAdapter {
    @Override
    default IEntity entity() {
        return item();
    }

    IItem item();

    Item minecraftItem();

    IItemRendererImpl itemRenderer();
}
