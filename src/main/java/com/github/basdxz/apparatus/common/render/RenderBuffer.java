package com.github.basdxz.apparatus.common.render;

import lombok.*;
import lombok.experimental.*;

import java.nio.ByteBuffer;

@Getter
@Accessors(fluent = true, chain = true)
public class RenderBuffer<LAYOUT extends IRenderBufferLayout> implements IRenderBuffer<LAYOUT> {
    protected final IRenderBufferID<LAYOUT> bufferID;
    protected final ByteBuffer byteBuffer;

    public RenderBuffer(@NonNull IRenderBufferID<LAYOUT> bufferID, @NonNull ByteBuffer byteBuffer) {
        this.bufferID = bufferID;
        this.byteBuffer = byteBuffer;
        ensureByteBufferSize();
    }
}
