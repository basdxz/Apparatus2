package com.github.basdxz.apparatus.common.render;

import lombok.*;

//TODO: hash
public interface IRenderBufferInfo {
    default boolean renderBufferInfoEqual(@NonNull IRenderBufferInfo bufferInfo) {
        return bufferName().equals(bufferInfo.bufferName()) &&
                layout().renderBufferLayoutEqual(bufferInfo.layout());
    }

    IRenderBufferLayout layout();

    String bufferName();

    int byteSize();
}
