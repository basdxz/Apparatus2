package com.github.basdxz.apparatus.adapter.resource.impl;

import com.github.basdxz.apparatus.adapter.resource.IIconResourceAdapter;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import org.joml.Matrix3f;
import org.joml.Matrix3fc;

@Getter
@Accessors(fluent = true, chain = true)
public class IconResourceAdapter implements IIconResourceAdapter {
    protected final ILocation<ITextureResource> location;
    protected final Matrix3fc textureTransform;
    protected final TextureMap textureMap;

    public IconResourceAdapter(@NonNull ILocation<ITextureResource> location, @NonNull TextureMap textureMap) {
        this.location = location;
        this.textureMap = textureMap;
        textureTransform = newTextureTransform();
    }

    protected Matrix3fc newTextureTransform() {
        val icon = icon();
        val minU = icon.getMinU();
        val minV = icon.getMinV();
        val sizeU = icon.getMaxU() - minU;
        val sizeV = icon.getMaxV() - minV;
        return new Matrix3f(sizeU, 0F, minU,
                            0F, sizeV, minV,
                            0F, 0F, 1F);
    }

    protected IIcon icon() {
        return textureMap.registerIcon(location.domain() + ":" + location.path().replace("textures/items/", ""));
    }
}
