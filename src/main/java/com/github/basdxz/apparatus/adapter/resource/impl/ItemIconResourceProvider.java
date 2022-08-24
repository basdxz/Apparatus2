package com.github.basdxz.apparatus.adapter.resource.impl;

import com.github.basdxz.apparatus.adapter.resource.ResourceProviderAdapter;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.domain.IResourceContainer;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;


@RequiredArgsConstructor
public class ItemIconResourceProvider extends ResourceProviderAdapter<ITextureResource> {
    @NonNull
    protected final IIconRegister iconRegister;

    @Override
    protected boolean isSupported(@NonNull IResourceContainer<?> container) {
        return false;
    }

    @Override
    protected ITextureResource newResource(ILocation<ITextureResource> location) {
        return null;
    }
}
