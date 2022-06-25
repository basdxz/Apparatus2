package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.domain.impl.Location;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resource.impl.ResourceType.TEXTURE;

@Data
@Accessors(fluent = true, chain = true)
public class TextureResource implements ITextureResource {
    protected final ILocation location;

    public static ITextureResource newDefaultTextureResource(@NonNull IDomain domain, @NonNull String path) {
        return new TextureResource(new Location(domain, path));
    }

    @Override
    public IResourceType type() {
        return TEXTURE;
    }
}
