package com.github.basdxz.apparatus.adapter.resource;

import com.github.basdxz.apparatus.common.resource.ITextureAtlasIcon;
import net.minecraft.client.renderer.texture.TextureMap;

public interface ITextureAtlasIconAdapter extends ITextureAtlasIcon {
    TextureMap textureMap();
}
