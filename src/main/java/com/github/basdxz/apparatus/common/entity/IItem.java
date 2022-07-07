package com.github.basdxz.apparatus.common.entity;

public interface IItem extends IEntity {
    String ITEM_TYPE_NAME = "item";

    @Override
    default String typeName() {
        return ITEM_TYPE_NAME;
    }
}
