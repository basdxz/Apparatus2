package com.github.basdxz.apparatus.common.render;

import lombok.*;

//TODO: hash
public interface IRenderBufferInfo<LAYOUT extends IRenderBufferLayout> {
    default boolean renderBufferInfoEqual(@NonNull IRenderBufferInfo<LAYOUT> bufferInfo) {
        return byteSize() == byteSize() &&
               layout().renderBufferLayoutEqual(bufferInfo.layout());
    }

    LAYOUT layout();

    int byteSize();
}
