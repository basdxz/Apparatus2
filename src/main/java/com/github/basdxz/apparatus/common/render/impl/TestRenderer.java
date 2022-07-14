package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderContext;
import com.github.basdxz.apparatus.common.render.IRenderer;
import com.github.basdxz.apparatus.common.render.IRendererInfo;
import lombok.*;

public class TestRenderer implements IRenderer {
    public static IRenderer INSTANCE = new TestRenderer();

    @Override
    public IRendererInfo info() {
        return new IRendererInfo() {
            @Override
            public int maxBufferCount() {
                return 1;
            }

            @Override
            public int maxBufferByteSize() {
                return TestModel.requiredBufferByteSize();
            }
        };
    }

    @Override
    public void render(@NonNull IRenderContext context) {
        val byteBuffer = context.byteBuffer(TestRenderBufferInfo.INSTANCE);

        context.render(new TestBufferedModel(byteBuffer, TestModel.INSTANCE));
    }
}
