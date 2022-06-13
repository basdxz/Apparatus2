package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.resource.ISpriteModel;
import lombok.*;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import org.lwjgl.opengl.*;

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
        if (sprite.thickness() != 0F) {
            renderIconFlat();
        } else {
            renderSpriteWithThickness();
        }
    }

    public void renderIconFlat() {
        GL11.glPushAttrib(GL11.GL_POLYGON_BIT);
        GL11.glDisable(GL11.GL_CULL_FACE);
        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.setNormal(0.0F, 1.0F, 0.0F);
        Tessellator.instance.addVertexWithUV(0F, 0F, 0.0D, textureAdapter.getMinU(), textureAdapter.getMaxV());
        Tessellator.instance.addVertexWithUV(1F, 0F, 0.0D, textureAdapter.getMaxU(), textureAdapter.getMaxV());
        Tessellator.instance.addVertexWithUV(1F, 1F, 0.0D, textureAdapter.getMaxU(), textureAdapter.getMinV());
        Tessellator.instance.addVertexWithUV(0F, 1F, 0.0D, textureAdapter.getMinU(), textureAdapter.getMinV());
        Tessellator.instance.draw();
        GL11.glPopAttrib();
    }

    protected void renderSpriteWithThickness() {
        renderSpriteWithThicknessReal(
                textureAdapter.getMaxU(), textureAdapter.getMinV(),
                textureAdapter.getMinU(), textureAdapter.getMaxV(),
                textureAdapter.getIconWidth(), textureAdapter.getIconHeight(),
                SPRITE_UNIT_THICKNESS * sprite.thickness()
        );
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

        Tessellator.instance.setNormal(0F, 1F, 0F);
        for (var i = 0; i < height; i++) {
            val heightFraction = (float) i / (float) height;
            val posY = heightFraction + 1.0F / (float) height;
            val posV = maxV + (minV - maxV) * heightFraction - floatB;
            Tessellator.instance.addVertexWithUV(0D, posY, 0D, maxU, posV);
            Tessellator.instance.addVertexWithUV(1D, posY, 0D, minU, posV);
            Tessellator.instance.addVertexWithUV(1D, posY, -thickness, minU, posV);
            Tessellator.instance.addVertexWithUV(0D, posY, -thickness, maxU, posV);
        }


        Tessellator.instance.setNormal(0F, -1F, 0F);
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
