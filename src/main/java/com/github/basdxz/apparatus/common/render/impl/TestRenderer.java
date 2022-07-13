package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderContext;
import com.github.basdxz.apparatus.common.render.IRenderer;
import com.github.basdxz.apparatus.common.render.IRendererInfo;
import lombok.*;

import static com.github.basdxz.apparatus.common.render.BufferedModelUtil.VERTEX_FLOAT_SIZE;

public class TestRenderer implements IRenderer {
    public static IRenderer INSTANCE = new TestRenderer();

    @Override
    public IRendererInfo info() {
        return null;
    }

    @Override
    public void render(@NonNull IRenderContext context) {
        val byteBuffer =
                context.byteBuffer(TestModel.INSTANCE.faces().size() * 3 * VERTEX_FLOAT_SIZE * 4);

        context.render(new TestBufferedModel(byteBuffer, TestModel.INSTANCE));
    }
}
