package com.github.basdxz.apparatus.common.render;

import lombok.*;

//TODO: hash
public interface IRenderBufferInfo {
    default boolean renderBufferInfoEqual(@NonNull IRenderBufferInfo renderBufferInfo) {
        return renderBufferName().equals(renderBufferInfo.renderBufferName()) ||
                layout().renderBufferLayoutEqual(renderBufferInfo.layout());
    }

    IRenderBufferLayout layout();

    String renderBufferName();

    int byteSize();
}
