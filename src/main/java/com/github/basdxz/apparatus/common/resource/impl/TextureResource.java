package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IResourceLocation;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resource.impl.ResourceType.TEXTURE;

@Data
@Accessors(fluent = true, chain = true)
public class TextureResource implements ITextureResource {
    protected final IResourceLocation location;

    public static ITextureResource newDefaultTextureResource(@NonNull String domain, @NonNull String path) {
        return new TextureResource(new ResourceLocation(domain, path));
    }

    @Override
    public IResourceType type() {
        return TEXTURE;
    }
}
