package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderHandler;
import com.github.basdxz.apparatus.common.render.IRenderView;
import com.github.basdxz.apparatus.common.render.IRenderer;
import com.github.basdxz.apparatus.common.resource.IResourceProvider;
import lombok.*;

public class TestRenderer implements IRenderer {
    public static IRenderer INSTANCE = new TestRenderer();

    @Override
    public void register(@NonNull IResourceProvider resourceProvider) {

    }

    @Override
    public void render(@NonNull IRenderHandler renderHandler, @NonNull IRenderView rendererView) {
        renderHandler.render(TestModel.INSTANCE);
    }
}
