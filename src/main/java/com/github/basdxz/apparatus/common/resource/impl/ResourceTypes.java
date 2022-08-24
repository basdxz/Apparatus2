package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IResourceType;
import com.github.basdxz.apparatus.common.resource.ITexture;
import com.github.basdxz.apparatus.common.resource.ITextureAtlasIcon;
import lombok.experimental.*;

@UtilityClass
public final class ResourceTypes {
    public static final String PNG_EXTENSION = "png";
    public static final String OBJ_EXTENSION = "obj";

    public static final IResourceType<ITexture> TEXTURE = new ResourceType<>(ITexture.class, PNG_EXTENSION);
    public static final IResourceType<ITextureAtlasIcon> TEXTURE_ATLAS_ICON = new ResourceType<>(ITextureAtlasIcon.class, PNG_EXTENSION);
}
