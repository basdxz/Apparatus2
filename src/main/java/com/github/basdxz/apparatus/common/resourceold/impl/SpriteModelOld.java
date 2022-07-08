package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.resourceold.IModelProperties;
import com.github.basdxz.apparatus.common.resourceold.ISpriteModelOld;
import com.github.basdxz.apparatus.common.resourceold.ITextureResourceOld;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resourceold.impl.ModelPropertiesOld.newDefaultProperties;
import static com.github.basdxz.apparatus.common.resourceold.impl.TextureResourceOld.newDefaultTextureResource;

@Data
@Deprecated
@Accessors(fluent = true, chain = true)
public class SpriteModelOld implements ISpriteModelOld {
    @NonNull
    protected final IModelProperties properties;
    @NonNull
    protected final ITextureResourceOld texture;
    protected final float thickness;

    public static ISpriteModelOld newDefaultSpriteModel(@NonNull IDomain domain, @NonNull String path) {
        return new SpriteModelOld(
                newDefaultProperties(domain, path),
                newDefaultTextureResource(domain, path),
                1F
        );
    }
}
