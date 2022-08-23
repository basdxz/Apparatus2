package com.github.basdxz.apparatus.adapter.resource;

import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.IResourceManager;

public interface IResourceContainerHandlerAdapter {
    void init();

    void resetResources();

    void loadProperties(@NonNull IResourceManager resourceManager);

    void loadMeshes(@NonNull IResourceManager resourceManager);

    void loadTextures(@NonNull IResourceManager resourceManager);

    void loadBlockIcons(@NonNull IIconRegister iconRegister);

    void loadItemIcons(@NonNull IIconRegister iconRegister);

    void ensureAllResourcesLoaded();
}
