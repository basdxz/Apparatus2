package com.github.basdxz.apparatus.common.render;

import lombok.*;

public interface IRenderContext {
    <T extends IRenderBufferLayout> IRenderBuffer<T> getRenderBuffer(@NonNull IRenderBufferID<T> bufferID);

    void render(@NonNull IRenderBufferID<?> bufferID);
}
