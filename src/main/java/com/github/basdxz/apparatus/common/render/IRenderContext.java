package com.github.basdxz.apparatus.common.render;

import lombok.*;

public interface IRenderContext {
    IRenderBuffer byteBuffer(@NonNull IRenderBufferInfo bufferInfo);

    void render(@NonNull IBufferedModel bufferedModel);
}
