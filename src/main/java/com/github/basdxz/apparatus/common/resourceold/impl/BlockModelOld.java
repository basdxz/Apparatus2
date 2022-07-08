package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.resourceold.IBlockModelOld;
import com.github.basdxz.apparatus.common.resourceold.IModelProperties;
import com.github.basdxz.apparatus.common.resourceold.ITextureResourceOld;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resourceold.impl.ModelPropertiesOld.newDefaultProperties;
import static com.github.basdxz.apparatus.common.resourceold.impl.TextureResourceOld.newDefaultTextureResource;

@Data
@Deprecated
@Accessors(fluent = true, chain = true)
public class BlockModelOld implements IBlockModelOld {
    @NonNull
    protected final IModelProperties properties;
    @NonNull
    protected final ITextureResourceOld texture;

    public static IBlockModelOld newDefaultBlockModel(@NonNull IDomain domain, @NonNull String path) {
        return new BlockModelOld(
                newDefaultProperties(domain, path),
                newDefaultTextureResource(domain, path)
        );
    }
}
