package com.github.basdxz.apparatus.common.render;

import com.github.basdxz.apparatus.common.resource.IResourceProvider;
import lombok.*;

public interface IRenderer {
    void register(@NonNull IResourceProvider resourceProvider);

    void render(@NonNull IRenderHandler renderHandler, @NonNull IRenderView rendererView);
}
