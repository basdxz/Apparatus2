package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;
import lombok.experimental.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@RequiredArgsConstructor(access = PROTECTED)
@Accessors(fluent = true, chain = true)
public class TextureResourceType implements IResourceType {
    public static final TextureResourceType INSTANCE = new TextureResourceType();
    protected static final String TEXTURE_EXTENSION = "png";

    @Override
    public String extension() {
        return TEXTURE_EXTENSION;
    }

    @Override
    public String toString() {
        return resourceTypeToString();
    }
}
