package com.github.basdxz.apparatus.adapter.resource;

import lombok.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;

public interface IResourceContainerHandlerAdapter {
    void init();

    void resetResources();

    void loadProperties(@NonNull IResourceManager resourceManager);

    void loadMeshes(@NonNull IResourceManager resourceManager);

    void loadTextures(@NonNull IResourceManager resourceManager);

    void loadBlockIcons(@NonNull TextureMap textureMap);

    void loadItemIcons(@NonNull TextureMap textureMap);

    void ensureAllResourcesLoaded();
}
