package com.github.basdxz.apparatus.common.render;

public interface IRenderModel<INSTANCE extends IRenderModelInstance> {
    INSTANCE newModelInstance();
}
