package com.github.basdxz.apparatus.adapter.resource.impl;

import lombok.*;
import lombok.experimental.*;
import net.minecraft.client.renderer.texture.TextureMap;

@UtilityClass
public final class TextureMapHelper {
    private static final String[] TEXTURE_MAP_PATH_PREFIX = new String[]{"textures/blocks/", "textures/items/"};

    static boolean isPathValid(@NonNull String path, @NonNull TextureMap textureMap) {
        return path.startsWith(textureMapPrefix(textureMap));
    }

    static String trimTextureMapPrefix(@NonNull String path, @NonNull TextureMap textureMap) {
        return path.substring(textureMapPrefix(textureMap).length());
    }

    private static String textureMapPrefix(@NonNull TextureMap textureMap) {
        return TEXTURE_MAP_PATH_PREFIX[textureMap.getTextureType()];
    }
}
