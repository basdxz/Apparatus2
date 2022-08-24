package com.github.basdxz.apparatus.adapter.resource.impl;

import com.github.basdxz.apparatus.adapter.resource.ITextureAtlasIconAdapter;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resource.ITextureAtlasIcon;
import lombok.*;
import lombok.experimental.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import org.joml.Matrix3f;
import org.joml.Matrix3fc;

@Getter
@Accessors(fluent = true, chain = true)
public class TextureAtlasIconAdapter implements ITextureAtlasIconAdapter {
    protected final ILocation<ITextureAtlasIcon> location;
    protected final TextureMap textureMap;
    protected final IIcon icon;
    @Getter(AccessLevel.NONE)
    protected Matrix3fc textureTransform;

    public TextureAtlasIconAdapter(@NonNull ILocation<ITextureAtlasIcon> location, @NonNull TextureMap textureMap) {
        this.location = location;
        this.textureMap = textureMap;
        icon = icon();
    }

    protected IIcon icon() {
        return textureMap.registerIcon(location.domain() + ":" +
                                       TextureMapHelper.trimTextureMapPrefix(location.path(), textureMap));
    }

    @Override
    public Matrix3fc textureTransform() {
        initTextureTransformIfNull();
        return textureTransform;
    }

    protected void initTextureTransformIfNull() {
        if (textureTransform == null)
            textureTransform = newTextureTransform();
    }

    protected Matrix3fc newTextureTransform() {
        val minU = icon.getMinU();
        val minV = icon.getMinV();
        val sizeU = icon.getMaxU() - minU;
        val sizeV = icon.getMaxV() - minV;
        return newScaleTranslationMatrix3f(sizeU, sizeV, minU, minV);
    }

    protected Matrix3f newScaleTranslationMatrix3f(float scaleX, float scaleY, float translationX, float translationY) {
        return new Matrix3f(scaleX, 0F, 0F,
                            0F, scaleY, 0F,
                            translationX, translationY, 1F);
    }
}
