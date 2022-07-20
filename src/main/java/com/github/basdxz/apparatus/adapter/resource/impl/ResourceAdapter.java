package com.github.basdxz.apparatus.adapter.resource.impl;

import com.github.basdxz.apparatus.common.domain.impl.DomainRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import lombok.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraftforge.client.event.TextureStitchEvent;

//TODO: Replace all this with Mixin hooks
public class ResourceAdapter implements IResourceManagerReloadListener {
    public static final ResourceAdapter INSTANCE = new ResourceAdapter();

    @Override
    public void onResourceManagerReload(@NonNull IResourceManager resourceManager) {
        resetResources();
        registerProperties(resourceManager);
        registerMeshes(resourceManager);
        registerTextures(resourceManager);
    }

    protected void resetResources() {
        System.out.println("(1) Reset resources!");
        DomainRegistry.INSTANCE.resetResources();
    }

    protected void registerProperties(@NonNull IResourceManager resourceManager) {
        System.out.println("(2) Register Properties!");
        DomainRegistry.INSTANCE.registerResources(System.out::println);
    }

    protected void registerMeshes(@NonNull IResourceManager resourceManager) {
        System.out.println("(3) Register Meshes!");
        DomainRegistry.INSTANCE.registerResources(System.out::println);
    }

    protected void registerTextures(@NonNull IResourceManager resourceManager) {
        System.out.println("(4) Register Textures!");
        DomainRegistry.INSTANCE.registerResources(System.out::println);
    }

    @SubscribeEvent
    public void registerIcons(@NonNull TextureStitchEvent.Pre event) {
        val textureMap = event.map;
        val textureType = textureMap.getTextureType();
        if (textureType == 0) {
            registerBlockIcons(textureMap);
        } else if (textureType == 1) {
            registerItemIcons(textureMap);
        }
    }

    protected void registerBlockIcons(@NonNull TextureMap textureMap) {
        System.out.println("(5) Registering block icons!");
        DomainRegistry.INSTANCE.registerResources(System.out::println);
    }

    protected void registerItemIcons(@NonNull TextureMap textureMap) {
        System.out.println("(6) Registering item icons!");
        DomainRegistry.INSTANCE.registerResources(System.out::println);
    }

    protected void ensureAllResourcesRegistered() {
        System.out.println("(7) Ensure all resources registered!");
        DomainRegistry.INSTANCE.ensureAllResourcesRegistered();
    }
}
