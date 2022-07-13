package com.github.basdxz.apparatus.common.render;

import lombok.*;

import java.nio.ByteBuffer;

public interface IRenderContext {
    ByteBuffer byteBuffer(int byteSize);

    void render(@NonNull IBufferedModel bufferedModel);
}
