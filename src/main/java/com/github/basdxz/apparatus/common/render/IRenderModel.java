package com.github.basdxz.apparatus.common.render;

import lombok.*;

public interface IRenderModel<LAYOUT extends IRenderBufferLayout, INFO extends IRenderModelInfo> {
    IRenderBufferID<LAYOUT> newRenderBufferID();

    INFO newModelInfo();

    IBufferedModel bufferModel(@NonNull IRenderBuffer<LAYOUT> renderBuffer, @NonNull INFO modelInfo);
}
