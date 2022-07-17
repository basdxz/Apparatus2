package com.github.basdxz.apparatus.adapter.render.impl;

import com.github.basdxz.apparatus.common.render.IModel;
import com.github.basdxz.apparatus.common.render.IRenderHandlerOld;
import com.github.basdxz.apparatus.common.render.IVertex;
import lombok.*;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.*;

public class TestRenderHandlerOld implements IRenderHandlerOld {
    public static IRenderHandlerOld INSTANCE = new TestRenderHandlerOld();

    @Override
    public void render(@NonNull IModel model) {
//        renderIcon(0, 0, Items.apple.getIconFromDamage(0), 16, 16);
//        renderSquare(0, 0, 16, 16);

        renderModel(model);
    }


    public void renderModel(@NonNull IModel model) {
        Tessellator.instance.startDrawing(GL11.GL_TRIANGLES);
        model.faces().stream()
             .flatMap((face) -> face.verts().stream())
             .forEach(this::addVert);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Tessellator.instance.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public void addVert(@NonNull IVertex vert) {
        val normal = vert.normal();
        val color = vert.color();
        val position = vert.position();
        val texture = vert.texture();

        Tessellator.instance.setNormal(normal.x(), normal.y(), normal.z());
        Tessellator.instance.setColorRGBA_F(color.x(), color.y(), color.z(), color.w());
        Tessellator.instance.addVertexWithUV(position.x(), position.y(), position.z(), texture.x(), texture.y());
    }

    public void renderSquare(int posX, int posY, int width, int height) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(255, 0, 0, 255);
        tessellator.addVertex(posX + 0, posY + height, 0D);
        tessellator.addVertex(posX + width, posY + height, 0D);
        tessellator.addVertex(posX + width, posY + 0, 0D);
        tessellator.addVertex(posX + 0, posY + 0, 0D);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        tessellator.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public void renderIcon(int posX, int posY, @NonNull IIcon icon, int width, int height) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(posX + 0, posY + height, 0D, icon.getMinU(), icon.getMaxV());
        tessellator.addVertexWithUV(posX + width, posY + height, 0D, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(posX + width, posY + 0, 0D, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(posX + 0, posY + 0, 0D, icon.getMinU(), icon.getMinV());
        tessellator.draw();
    }
}
