package com.github.basdxz.apparatus.example;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IItem;

//Bow that shoots every arrow in the inventory at once
public class BFGTheBow implements IItem {
    @Override
    public IEntityID entityID() {
        return null;
    }

    @Override
    public String localizedName() {
        return null;
    }
}
