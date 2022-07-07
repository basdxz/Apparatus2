package com.github.basdxz.apparatus.tiger.adapter.item.impl;

import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.tiger.adapter.item.IItemAdapter;
import com.github.basdxz.apparatus.tiger.adapter.render.IItemRendererImpl;
import com.github.basdxz.apparatus.tiger.adapter.render.impl.ItemRendererImpl;
import lombok.*;
import lombok.experimental.*;
import net.minecraft.item.Item;

@Getter
@Accessors(fluent = true, chain = true)
public class ItemAdapter implements IItemAdapter {
    protected final IItem item;
    protected final Item minecraftItem;
    protected final IItemRendererImpl itemRenderer;

    public ItemAdapter(@NonNull IItem item) {
        this.item = item;
        minecraftItem = newMinecraftItem();
        itemRenderer = newItemRenderer();
    }

    protected Item newMinecraftItem() {
        return new ItemImpl(this);
    }

    protected IItemRendererImpl newItemRenderer() {
        return new ItemRendererImpl(this);
    }
}
