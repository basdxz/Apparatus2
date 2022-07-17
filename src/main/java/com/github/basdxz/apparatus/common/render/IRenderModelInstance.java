package com.github.basdxz.apparatus.common.render;

import lombok.*;

public interface IRenderModelInstance {
    default IRenderBufferLayout bufferLayout() {
        return bufferID().bufferLayout();
    }

    IRenderBufferID<?> bufferID();

    void render(@NonNull IRenderContext context);
}
