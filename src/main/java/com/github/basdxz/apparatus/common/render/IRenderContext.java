package com.github.basdxz.apparatus.common.render;

import lombok.*;

public interface IRenderContext {
    IRenderBuffer<BasicRenderBufferLayout> byteBuffer(@NonNull IRenderBufferID<BasicRenderBufferLayout> id);

    void render(@NonNull IBufferedModel bufferedModel);
}
