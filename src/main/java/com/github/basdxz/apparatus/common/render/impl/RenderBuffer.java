package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBuffer;
import com.github.basdxz.apparatus.common.render.IRenderBufferID;
import com.github.basdxz.apparatus.common.render.IRenderBufferLayout;
import lombok.*;
import lombok.experimental.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

@Getter
@Accessors(fluent = true, chain = true)
public class RenderBuffer<LAYOUT extends IRenderBufferLayout> implements IRenderBuffer<LAYOUT> {
    protected final IRenderBufferID<LAYOUT> bufferID;
    protected final ByteBuffer byteBuffer;
    protected final ShortBuffer shortBuffer;
    protected final IntBuffer intBuffer;
    protected final FloatBuffer floatBuffer;

    public RenderBuffer(@NonNull IRenderBufferID<LAYOUT> bufferID, @NonNull ByteBuffer byteBuffer) {
        this.bufferID = bufferID;
        this.byteBuffer = byteBuffer;
        ensureByteBufferSize();
        shortBuffer = byteBuffer.asShortBuffer();
        intBuffer = byteBuffer.asIntBuffer();
        floatBuffer = byteBuffer.asFloatBuffer();
    }
}
