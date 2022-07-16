package com.github.basdxz.apparatus.common.render;

import lombok.*;

//TODO: Hash
public interface IRenderBufferLayout {
    default boolean renderBufferLayoutEqual(@NonNull IRenderBufferLayout bufferLayout) {
        return layoutName().equals(bufferLayout.layoutName());
    }

    String layoutName();

    default int bufferByteSize(int vertexCount) {
        return vertexCount * vertexByteSize();
    }

    int vertexByteSize();
}
