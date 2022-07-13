package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderHandlerOld;
import com.github.basdxz.apparatus.common.render.IRenderView;
import com.github.basdxz.apparatus.common.render.IRendererOld;
import com.github.basdxz.apparatus.common.resource.IResourceProvider;
import lombok.*;

public class TestRendererOld implements IRendererOld {
    public static IRendererOld INSTANCE = new TestRendererOld();

    @Override
    public void register(@NonNull IResourceProvider resourceProvider) {

    }

    @Override
    public void render(@NonNull IRenderHandlerOld renderHandler, @NonNull IRenderView rendererView) {
        renderHandler.render(TestModel.INSTANCE);
    }
}
