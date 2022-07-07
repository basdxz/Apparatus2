package com.github.basdxz.apparatus.common.entity;

public interface ITile extends IItem {
    String TILE_TYPE_NAME = "tile";

    @Override
    default String typeName() {
        return TILE_TYPE_NAME;
    }
}
