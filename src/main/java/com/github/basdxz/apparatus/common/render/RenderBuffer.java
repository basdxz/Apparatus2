package com.github.basdxz.apparatus.common.render;

import lombok.*;
import lombok.experimental.*;

import java.nio.ByteBuffer;

@Getter
@Accessors(fluent = true, chain = true)
public class RenderBuffer implements IRenderBuffer {
    protected final IRenderBufferInfo bufferInfo;
    protected final ByteBuffer byteBuffer;

    public RenderBuffer(@NonNull IRenderBufferInfo bufferInfo, @NonNull ByteBuffer byteBuffer) {
        this.bufferInfo = bufferInfo;
        this.byteBuffer = byteBuffer;
        ensureByteBufferSize();
    }
}
