package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;

@Getter
@Accessors(fluent = true, chain = true)
@AllArgsConstructor
public class TextureResource implements ITextureResource {
    protected final String resourceDomain;
    protected final String resourceLocation;
}
