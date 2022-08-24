package com.github.basdxz.apparatus.adapter.resource.impl;

import com.github.basdxz.apparatus.adapter.resource.ResourceProvider;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.domain.IResourceContainer;
import com.github.basdxz.apparatus.common.resource.ITextureAtlasIcon;
import com.github.basdxz.apparatus.common.resource.impl.ResourceTypes;
import lombok.*;
import net.minecraft.client.renderer.texture.TextureMap;

@RequiredArgsConstructor
public class TextureAtlasIconProvider extends ResourceProvider<ITextureAtlasIcon> {
    @NonNull
    protected final TextureMap textureMap;

    @Override
    protected boolean isSupported(@NonNull IResourceContainer<?> container) {
        return TextureMapHelper.isPathValid(container.location().path(), textureMap) &&
               resourceClass().isAssignableFrom(resourceClass());
    }

    @Override
    protected Class<ITextureAtlasIcon> resourceClass() {
        return ResourceTypes.TEXTURE_ATLAS_ICON.resourceClass();
    }

    @Override
    protected ITextureAtlasIcon newResource(@NonNull ILocation<ITextureAtlasIcon> location) {
        return new TextureAtlasIconAdapter(location, textureMap);
    }
}
