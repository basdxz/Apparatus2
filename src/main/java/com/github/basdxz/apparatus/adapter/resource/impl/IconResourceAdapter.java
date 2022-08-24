package com.github.basdxz.apparatus.adapter.resource.impl;

import com.github.basdxz.apparatus.adapter.resource.IIconResourceAdapter;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import org.joml.Matrix3fc;

@Getter
@Accessors(fluent = true, chain = true)
public class IconResourceAdapter implements IIconResourceAdapter {
    protected final ILocation<ITextureResource> location;
    protected final Matrix3fc textureTransform;

    public IconResourceAdapter(@NonNull ILocation<ITextureResource> location, @NonNull IIconRegister iconRegister) {
        this.location = location;
        textureTransform = newTextureTransform(iconRegister);
    }

    protected Matrix3fc newTextureTransform(@NonNull IIconRegister iconRegister) {
        val icon = icon(iconRegister);
        return null;
    }

    protected IIcon icon(@NonNull IIconRegister iconRegister) {
        return iconRegister.registerIcon(location.domain() + ":" + location.path());
    }
}
