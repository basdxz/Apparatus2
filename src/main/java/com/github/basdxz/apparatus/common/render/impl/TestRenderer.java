package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderContext;
import com.github.basdxz.apparatus.common.render.IRenderer;
import com.github.basdxz.apparatus.common.render.IRendererInfo;
import lombok.*;

public class TestRenderer implements IRenderer {
    public static IRenderer INSTANCE = new TestRenderer();

    protected final TestRenderModel.TestRenderModelInstance modelInstance = TestRenderModel.INSTANCE.newModelInstance();

    @Override
    public IRendererInfo info() {
        return new IRendererInfo() {
            @Override
            public int maxBufferCount() {
                return 1;
            }

            @Override
            public int maxBufferByteSize() {
                return TestRenderModel.requiredBufferByteSize();
            }
        };
    }

    @Override
    public void render(@NonNull IRenderContext context) {
        modelInstance.color().set(0F, 1F, 1F, 1F);
        modelInstance.render(context);
    }
}
