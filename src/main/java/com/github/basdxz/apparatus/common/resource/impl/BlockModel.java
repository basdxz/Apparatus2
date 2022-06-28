package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.resource.IBlockModel;
import com.github.basdxz.apparatus.common.resource.IModelProperties;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resource.impl.ModelProperties.newDefaultProperties;
import static com.github.basdxz.apparatus.common.resource.impl.TextureResource.newDefaultTextureResource;

@Data
@Accessors(fluent = true, chain = true)
public class BlockModel implements IBlockModel {
    @NonNull
    protected final IModelProperties properties;
    @NonNull
    protected final ITextureResource texture;

    public static IBlockModel newDefaultBlockModel(@NonNull IDomain domain, @NonNull String path) {
        return new BlockModel(
                newDefaultProperties(domain, path),
                newDefaultTextureResource(domain, path)
        );
    }
}
