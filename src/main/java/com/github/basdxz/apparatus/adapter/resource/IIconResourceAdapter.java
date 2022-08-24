package com.github.basdxz.apparatus.adapter.resource;

import com.github.basdxz.apparatus.common.resource.ITextureResource;
import net.minecraft.client.renderer.texture.TextureMap;

public interface IIconResourceAdapter extends ITextureResource {
    TextureMap textureMap();
}
