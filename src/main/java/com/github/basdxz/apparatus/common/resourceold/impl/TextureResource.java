package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resourceold.IResourceTypeOld;
import com.github.basdxz.apparatus.common.resourceold.ITextureResource;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resourceold.impl.ResourceTypeOld.TEXTURE;

@Data
@Accessors(fluent = true, chain = true)
public class TextureResource implements ITextureResource {
    protected final ILocation location;

    public static ITextureResource newDefaultTextureResource(@NonNull IDomain domain, @NonNull String path) {
        return new TextureResource(domain.location(path));
    }

    @Override
    public IResourceTypeOld type() {
        return TEXTURE;
    }
}
