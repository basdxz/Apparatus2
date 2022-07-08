package com.github.basdxz.apparatus.common.render;

import com.github.basdxz.apparatus.common.resourceold.IResource;
import com.github.basdxz.apparatus.common.resourceold.IResourceProvider;
import lombok.*;

import java.util.Set;

public interface IEntityRenderer {
    void register(@NonNull IResourceProvider resourceProvider);

    Set<IResource> resources();

    void render(@NonNull IRenderHandler renderHandler, @NonNull IRendererView rendererView);
}
