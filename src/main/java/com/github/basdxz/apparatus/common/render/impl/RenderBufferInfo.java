package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBufferInfo;
import com.github.basdxz.apparatus.common.render.IRenderBufferLayout;
import lombok.*;
import lombok.experimental.*;

@Getter
@Accessors(fluent = true, chain = true)
public class RenderBufferInfo<T extends IRenderBufferLayout> implements IRenderBufferInfo<T> {
    protected final T bufferLayout;
    protected final int vertexCount;
    protected final int byteSize;

    public RenderBufferInfo(@NonNull T bufferLayout, int vertexCount) {
        this.bufferLayout = bufferLayout;
        this.vertexCount = vertexCount;
        byteSize = vertexCount * bufferLayout.vertexByteSize();
    }
}
