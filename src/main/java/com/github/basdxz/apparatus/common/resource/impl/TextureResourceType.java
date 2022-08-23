package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IResourceType;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Accessors(fluent = true, chain = true)
@RequiredArgsConstructor(access = PROTECTED)
public class TextureResourceType implements IResourceType<ITextureResource> {
    protected static final String TEXTURE_EXTENSION = "png";

    @Override
    public Class<ITextureResource> resourceBaseClass() {
        return ITextureResource.class;
    }

    @Override
    public String fileExtension() {
        return TEXTURE_EXTENSION;
    }
}
