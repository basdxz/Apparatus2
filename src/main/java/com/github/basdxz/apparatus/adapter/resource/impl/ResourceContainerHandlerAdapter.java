package com.github.basdxz.apparatus.adapter.resource.impl;

import com.github.basdxz.apparatus.adapter.resource.IResourceContainerHandlerAdapter;
import com.github.basdxz.apparatus.common.domain.IResourceProvider;
import com.github.basdxz.apparatus.common.domain.impl.DomainRegistry;
import com.github.basdxz.apparatus.common.render.impl.TestRenderer;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.IResourceManager;

//TODO: Replace all this with Mixin hooks
//TODO: Add stuff like lang/audio?
public class ResourceContainerHandlerAdapter implements IResourceContainerHandlerAdapter {
    public static final IResourceContainerHandlerAdapter INSTANCE = new ResourceContainerHandlerAdapter();

    protected boolean initialized = false;

    {
        //noinspection ResultOfMethodCallIgnored
        TestRenderer.INSTANCE.toString();//TODO: Remove, this is here just to keep it loaded early enough for resources to load properly.
    }

    @Override
    public void init() {
        initialized = true;
    }

    @Override
    public void resetResources() {
        if (!initialized)
            return;
        DomainRegistry.INSTANCE.resetResources();
    }

    @Override
    public void loadProperties(@NonNull IResourceManager resourceManager) {
        if (!initialized)
            return;
        DomainRegistry.INSTANCE.loadResources(System.out::println);
    }

    @Override
    public void loadMeshes(@NonNull IResourceManager resourceManager) {
        if (!initialized)
            return;
        DomainRegistry.INSTANCE.loadResources(System.out::println);
    }

    @Override
    public void loadTextures(@NonNull IResourceManager resourceManager) {
        if (!initialized)
            return;
        DomainRegistry.INSTANCE.loadResources(System.out::println);
    }

    @Override
    public void loadBlockIcons(@NonNull IIconRegister iconRegister) {
        if (initialized)
            DomainRegistry.INSTANCE.loadResources(newBlockIconResourceProvider(iconRegister));
    }

    @Override
    public void loadItemIcons(@NonNull IIconRegister iconRegister) {
        if (initialized)
            DomainRegistry.INSTANCE.loadResources(newItemIconResourceProvider(iconRegister));
    }

    @Override
    public void ensureAllResourcesLoaded() {
        if (!initialized)
            return;
        DomainRegistry.INSTANCE.ensureAllResourcesLoaded();
    }

    protected IResourceProvider newBlockIconResourceProvider(@NonNull IIconRegister iconRegister) {
        return new BlockIconResourceProvider(iconRegister);
    }

    protected IResourceProvider newItemIconResourceProvider(@NonNull IIconRegister iconRegister) {
        return new ItemIconResourceProvider(iconRegister);
    }
}
