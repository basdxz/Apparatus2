package com.github.basdxz.apparatus.common.entity;

public interface IFluid extends ITile {
    String FLUID_TYPE_NAME = "fluid";

    @Override
    default String typeName() {
        return FLUID_TYPE_NAME;
    }
}
