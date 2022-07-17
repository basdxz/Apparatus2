package com.github.basdxz.apparatus.common.render;

import lombok.*;

//TODO: hash
public interface IRenderBufferInfo<LAYOUT extends IRenderBufferLayout> {
    default boolean renderBufferInfoEqual(@NonNull IRenderBufferInfo<LAYOUT> bufferInfo) {
        return byteSize() == byteSize() &&
               bufferLayout().renderBufferLayoutEqual(bufferInfo.bufferLayout());
    }

    LAYOUT bufferLayout();

    int byteSize();

    int vertexCount();
}
