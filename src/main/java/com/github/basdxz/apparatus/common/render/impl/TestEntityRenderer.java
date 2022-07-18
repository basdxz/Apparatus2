package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IEntityRenderer;
import com.github.basdxz.apparatus.common.render.IRenderView;
import com.github.basdxz.apparatus.common.render.IRenderer;
import lombok.*;

import java.util.Collections;
import java.util.List;

public class TestEntityRenderer implements IEntityRenderer {
    public static final IEntityRenderer INSTANCE = new TestEntityRenderer();

    @Override
    public List<IRenderer> renderers() {
        return Collections.singletonList(TestRenderer.INSTANCE);
    }

    @Override
    public IRenderer renderer(@NonNull IRenderView view) {
        return TestRenderer.INSTANCE;
    }
}
