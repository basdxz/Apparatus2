package com.github.basdxz.apparatus.common.render;

import lombok.*;

public interface IRenderModel<MODEL_INFO extends IRenderModelInfo> {
    MODEL_INFO newModelInfo();

    IBufferedModel bufferModel(@NonNull IRenderBuffer renderBuffer, @NonNull MODEL_INFO modelInfo);
}
