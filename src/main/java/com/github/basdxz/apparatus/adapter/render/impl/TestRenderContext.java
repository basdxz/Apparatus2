package com.github.basdxz.apparatus.adapter.render.impl;

import com.github.basdxz.apparatus.common.render.*;
import lombok.*;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import org.apache.commons.collections4.map.ReferenceMap;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
import java.util.stream.IntStream;

import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.HARD;
import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.WEAK;

public class TestRenderContext implements IRenderContext {
    public static IRenderContext INSTANCE = new TestRenderContext();

    //TODO: Decide on if this should be a WEAK or SOFT Reference, document intent???
    protected final Map<IRenderBufferInfo, IRenderBuffer> buffers = new ReferenceMap<>(HARD, WEAK, true);

    @Override
    public IRenderBuffer byteBuffer(@NonNull IRenderBufferInfo bufferInfo) {
        return buffers.computeIfAbsent(bufferInfo, this::newRenderBuffer);
    }

    protected IRenderBuffer newRenderBuffer(@NonNull IRenderBufferInfo bufferInfo) {
        return new RenderBuffer(bufferInfo, newByteBuffer(bufferInfo.byteSize()));
    }

    protected ByteBuffer newByteBuffer(int byteSize) {
        return ByteBuffer.allocateDirect(byteSize).order(ByteOrder.nativeOrder());
    }

    @Override
    public void render(@NonNull IBufferedModel bufferedModel) {
        renderModel(bufferedModel);
    }

    public void renderModel(@NonNull IBufferedModel bufferedModel) {
        Tessellator.instance.startDrawing(GL11.GL_TRIANGLES);

        IntStream.range(0, bufferedModel.vertexCount()).forEach(i -> addVertex(bufferedModel, i));

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Tessellator.instance.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    protected void addVertex(@NonNull IBufferedModel bufferedModel, int index) {
        Tessellator.instance.setNormal(
                bufferedModel.normalX(index),
                bufferedModel.normalY(index),
                bufferedModel.normalZ(index)
        );
        Tessellator.instance.setColorRGBA_F(
                bufferedModel.colorR(index),
                bufferedModel.colorG(index),
                bufferedModel.colorB(index),
                bufferedModel.colorA(index)
        );
        Tessellator.instance.setTextureUV(
                bufferedModel.textureU(index),
                bufferedModel.textureV(index)
        );
        Tessellator.instance.addVertex(
                bufferedModel.positionX(index),
                bufferedModel.positionY(index),
                bufferedModel.positionZ(index)
        );
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
