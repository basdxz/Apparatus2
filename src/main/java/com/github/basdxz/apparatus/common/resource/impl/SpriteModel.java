package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IModelProperties;
import com.github.basdxz.apparatus.common.resource.ISpriteModel;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public class SpriteModel implements ISpriteModel {
    protected final IModelProperties properties;
    protected final ITextureResource texture;
    protected final float thickness;
}
