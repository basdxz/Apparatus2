package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBufferInfo;
import com.github.basdxz.apparatus.common.render.IRenderBufferLayout;

public class TestRenderBufferInfo implements IRenderBufferInfo {
    public static final IRenderBufferInfo INSTANCE = new TestRenderBufferInfo();

    protected TestRenderBufferInfo() {
    }

    @Override
    public IRenderBufferLayout layout() {
        return RenderBufferLayout.MODEL_BUFFER_LAYOUT;
    }

    @Override
    public String bufferName() {
        return "test_buffer";
    }

    @Override
    public int byteSize() {
        return TestModel.requiredBufferByteSize();
    }
}
