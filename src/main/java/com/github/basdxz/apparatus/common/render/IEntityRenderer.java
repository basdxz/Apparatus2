package com.github.basdxz.apparatus.common.render;

import com.github.basdxz.apparatus.common.resource.IResource;
import com.github.basdxz.apparatus.common.resource.IResourceProvider;
import lombok.*;

import java.util.Set;

public interface IEntityRenderer {
    void register(@NonNull IResourceProvider resourceProvider);

    Set<IResource> resources();

    void render(@NonNull IRenderHandler renderHandler, @NonNull IRendererView rendererView);
}
