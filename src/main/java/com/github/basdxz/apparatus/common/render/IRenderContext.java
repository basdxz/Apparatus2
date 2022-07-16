package com.github.basdxz.apparatus.common.render;

import lombok.*;

public interface IRenderContext {
    IRenderBuffer<BasicRenderBufferLayout> getRenderBuffer(@NonNull IRenderBufferID<BasicRenderBufferLayout> bufferID);

    @Deprecated
    void render(@NonNull IBufferedModel bufferedModel);

    void render(@NonNull IRenderBufferID<BasicRenderBufferLayout> bufferID);
}
