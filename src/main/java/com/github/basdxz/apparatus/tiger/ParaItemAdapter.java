package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
import lombok.*;
import net.minecraft.item.Item;

public class ParaItemAdapter extends Item {
    protected final IParaItem paraItem;

    public ParaItemAdapter(@NonNull IParaItem paraItem) {
        this.paraItem = paraItem;
    }
}
