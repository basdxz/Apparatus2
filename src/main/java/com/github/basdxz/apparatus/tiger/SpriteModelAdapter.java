package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.resource.ISpriteModel;
import lombok.*;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class SpriteModelAdapter extends ModelAdapter {
    protected final static float SPRITE_UNIT_THICKNESS = 1F / 16F;

    protected final ISpriteModel sprite;
    protected final TextureAdapter textureAdapter;

    public SpriteModelAdapter(@NonNull ISpriteModel sprite, @NonNull IIconRegister iconRegister) {
        this.sprite = sprite;
        textureAdapter = new TextureAdapter(sprite.texture(), iconRegister);
    }

    @Override
    public void render() {
        renderSpriteWithThickness(textureAdapter, sprite.thickness());
    }

    protected static void renderSpriteWithThickness(@NonNull IIcon icon, float thickness) {
        renderSpriteWithThicknessReal(
                icon.getMaxU(), icon.getMinV(),
                icon.getMinU(), icon.getMaxV(),
                icon.getIconWidth(), icon.getIconHeight(),
                SPRITE_UNIT_THICKNESS * thickness);
    }

    protected static void renderSpriteWithThicknessReal(float maxU, float minV, float minU, float maxV, int width, int height, float thickness) {
        Tessellator.instance.startDrawingQuads();

        Tessellator.instance.setNormal(0F, 0F, 1F);
        Tessellator.instance.addVertexWithUV(0D, 0D, 0D, maxU, maxV);
        Tessellator.instance.addVertexWithUV(1D, 0D, 0D, minU, maxV);
        Tessellator.instance.addVertexWithUV(1D, 1D, 0D, minU, minV);
        Tessellator.instance.addVertexWithUV(0D, 1D, 0D, maxU, minV);

        Tessellator.instance.setNormal(0F, 0F, -1F);
        Tessellator.instance.addVertexWithUV(0D, 1D, -thickness, maxU, minV);
        Tessellator.instance.addVertexWithUV(1D, 1D, -thickness, minU, minV);
        Tessellator.instance.addVertexWithUV(1D, 0D, -thickness, minU, maxV);
        Tessellator.instance.addVertexWithUV(0D, 0D, -thickness, maxU, maxV);

        val floatA = 0.5F * (maxU - minU) / (float) width;
        val floatB = 0.5F * (maxV - minV) / (float) height;

        Tessellator.instance.setNormal(1F, 0F, 0F);
        for (var i = 0; i < width; i++) {
            val widthFraction = (float) i / (float) width;
            val posX = widthFraction + 1.0F / (float) width;
            val posU = maxU + (minU - maxU) * widthFraction - floatA;
            Tessellator.instance.addVertexWithUV(posX, 1D, -thickness, posU, minV);
            Tessellator.instance.addVertexWithUV(posX, 1D, 0D, posU, minV);
            Tessellator.instance.addVertexWithUV(posX, 0D, 0D, posU, maxV);
            Tessellator.instance.addVertexWithUV(posX, 0D, -thickness, posU, maxV);
        }

        Tessellator.instance.setNormal(-1F, 0F, 0F);
        for (var i = 0; i < width; i++) {
            val posX = (float) i / (float) width;
            val posU = maxU + (minU - maxU) * posX - floatA;
            Tessellator.instance.addVertexWithUV(posX, 0D, -thickness, posU, maxV);
            Tessellator.instance.addVertexWithUV(posX, 0D, 0D, posU, maxV);
            Tessellator.instance.addVertexWithUV(posX, 1D, 0D, posU, minV);
            Tessellator.instance.addVertexWithUV(posX, 1D, -thickness, posU, minV);
        }

        Tessellator.instance.setNormal(0.0F, 1.0F, 0.0F);
        for (var i = 0; i < height; i++) {
            val heightFraction = (float) i / (float) height;
            val posY = heightFraction + 1.0F / (float) height;
            val posV = maxV + (minV - maxV) * heightFraction - floatB;
            Tessellator.instance.addVertexWithUV(0D, posY, 0D, maxU, posV);
            Tessellator.instance.addVertexWithUV(1D, posY, 0D, minU, posV);
            Tessellator.instance.addVertexWithUV(1D, posY, -thickness, minU, posV);
            Tessellator.instance.addVertexWithUV(0D, posY, -thickness, maxU, posV);
        }


        Tessellator.instance.setNormal(0.0F, -1.0F, 0.0F);
        for (var i = 0; i < height; i++) {
            val posY = (float) i / (float) height;
            val posV = maxV + (minV - maxV) * posY - floatB;
            Tessellator.instance.addVertexWithUV(1D, posY, 0D, minU, posV);
            Tessellator.instance.addVertexWithUV(0D, posY, 0D, maxU, posV);
            Tessellator.instance.addVertexWithUV(0D, posY, -thickness, maxU, posV);
            Tessellator.instance.addVertexWithUV(1D, posY, -thickness, minU, posV);
        }

        Tessellator.instance.draw();
    }
}
