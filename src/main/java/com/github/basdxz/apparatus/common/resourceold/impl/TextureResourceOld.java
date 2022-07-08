package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resourceold.IResourceTypeOld;
import com.github.basdxz.apparatus.common.resourceold.ITextureResourceOld;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resourceold.impl.ResourceTypeOld.TEXTURE;

@Data
@Accessors(fluent = true, chain = true)
public class TextureResourceOld implements ITextureResourceOld {
    protected final ILocation location;

    public static ITextureResourceOld newDefaultTextureResource(@NonNull IDomain domain, @NonNull String path) {
        return new TextureResourceOld(domain.location(path));
    }

    @Override
    public IResourceTypeOld type() {
        return TEXTURE;
    }
}
