package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.resource.IModelProperties;
import com.github.basdxz.apparatus.common.resource.ISpriteModel;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resource.impl.ModelProperties.newDefaultProperties;
import static com.github.basdxz.apparatus.common.resource.impl.TextureResource.newDefaultTextureResource;

@Data
@Accessors(fluent = true, chain = true)
public class SpriteModel implements ISpriteModel {
    @NonNull
    protected final IModelProperties properties;
    @NonNull
    protected final ITextureResource texture;
    protected final float thickness;

    public static ISpriteModel newDefaultSpriteModel(@NonNull IDomain domain, @NonNull String path) {
        return new SpriteModel(
                newDefaultProperties(domain, path),
                newDefaultTextureResource(domain, path),
                1F
        );
    }
}
