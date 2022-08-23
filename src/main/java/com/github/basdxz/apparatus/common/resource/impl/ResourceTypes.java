package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IResourceType;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.experimental.*;

@UtilityClass
public final class ResourceTypes {
    public static final IResourceType<ITextureResource> TEXTURE = new TextureResourceType();
}
