package com.github.basdxz.apparatus.common.render;

import com.github.basdxz.apparatus.common.render.impl.BasicRenderBufferLayout;
import lombok.*;

public interface IRenderContext {
    IRenderBuffer<BasicRenderBufferLayout> getRenderBuffer(@NonNull IRenderBufferID<BasicRenderBufferLayout> bufferID);

    @Deprecated
    void render(@NonNull IBufferedModelOld bufferedModel);

    void render(@NonNull IRenderBufferID<?> bufferID);
}
