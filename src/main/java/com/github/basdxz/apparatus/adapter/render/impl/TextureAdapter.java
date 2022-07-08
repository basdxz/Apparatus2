package com.github.basdxz.apparatus.adapter.render.impl;

import com.github.basdxz.apparatus.common.resourceold.ITextureResourceOld;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class TextureAdapter implements IIcon {
    protected final IIcon icon;

    public TextureAdapter(@NonNull ITextureResourceOld texture, @NonNull IIconRegister iconRegister) {
        icon = iconRegister.registerIcon(
                texture.location().domain().domainName() + ":" + texture.location().path()
        );
    }

    @Override
    public int getIconWidth() {
        return icon.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return icon.getIconHeight();
    }

    @Override
    public float getMinU() {
        return icon.getMinU();
    }

    @Override
    public float getMaxU() {
        return icon.getMaxU();
    }

    @Override
    public float getInterpolatedU(double value) {
        return icon.getInterpolatedU(value);
    }

    @Override
    public float getMinV() {
        return icon.getMinV();
    }

    @Override
    public float getMaxV() {
        return icon.getMaxV();
    }

    @Override
    public float getInterpolatedV(double value) {
        return icon.getInterpolatedV(value);
    }

    @Override
    public String getIconName() {
        return icon.getIconName();
    }
}
