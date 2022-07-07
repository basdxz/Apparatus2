package com.github.basdxz.apparatus.adapter.entity;

import com.github.basdxz.apparatus.common.entity.IEntity;

public interface IEntityAdapter {
    IEntity entity();

    default String unlocalizedName() {
        return entity().unlocalizedName();
    }
}
