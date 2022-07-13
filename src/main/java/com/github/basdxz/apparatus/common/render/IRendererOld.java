package com.github.basdxz.apparatus.common.render;

import com.github.basdxz.apparatus.common.resource.IResourceProvider;
import lombok.*;

public interface IRendererOld {
    void register(@NonNull IResourceProvider resourceProvider);

    void render(@NonNull IRenderHandlerOld renderHandler, @NonNull IRenderView rendererView);
}
