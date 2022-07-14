package com.github.basdxz.apparatus.common.render;

import lombok.*;

//TODO: Hash
//TODO: Actual layout description
public interface IRenderBufferLayout {
    default boolean renderBufferLayoutEqual(@NonNull IRenderBufferLayout bufferLayout) {
        return renderBufferLayoutName().equals(bufferLayout.renderBufferLayoutName());
    }

    String renderBufferLayoutName();
}
