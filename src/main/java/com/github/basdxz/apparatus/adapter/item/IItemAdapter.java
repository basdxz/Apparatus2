package com.github.basdxz.apparatus.adapter.item;

import com.github.basdxz.apparatus.adapter.entity.IEntityAdapter;
import com.github.basdxz.apparatus.adapter.render.IItemRendererImpl;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.entity.IItem;
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
