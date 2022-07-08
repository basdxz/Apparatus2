package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.resourceold.IModelProperties;
import com.github.basdxz.apparatus.common.resourceold.ISpriteModel;
import com.github.basdxz.apparatus.common.resourceold.ITextureResource;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resourceold.impl.ModelProperties.newDefaultProperties;
import static com.github.basdxz.apparatus.common.resourceold.impl.TextureResource.newDefaultTextureResource;

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
