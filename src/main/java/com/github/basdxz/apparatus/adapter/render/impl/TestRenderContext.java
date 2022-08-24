package com.github.basdxz.apparatus.adapter.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBuffer;
import com.github.basdxz.apparatus.common.render.IRenderBufferID;
import com.github.basdxz.apparatus.common.render.IRenderBufferLayout;
import com.github.basdxz.apparatus.common.render.IRenderContext;
import com.github.basdxz.apparatus.common.render.impl.BasicRenderBufferLayout;
import com.github.basdxz.apparatus.common.render.impl.RenderBuffer;
import lombok.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import org.apache.commons.collections4.map.ReferenceMap;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;

import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.HARD;
import static org.apache.commons.collections4.map.AbstractReferenceMap.ReferenceStrength.WEAK;

public class TestRenderContext implements IRenderContext {
    public static IRenderContext INSTANCE = new TestRenderContext();

    //TODO: Decide on if this should be a WEAK or SOFT Reference, document intent???
    protected final Map<IRenderBufferID<?>, IRenderBuffer<?>> buffers = new ReferenceMap<>(HARD, WEAK, true);

    @Override
    @SuppressWarnings("unchecked")
    public <T extends IRenderBufferLayout> IRenderBuffer<T> getRenderBuffer(@NonNull IRenderBufferID<T> bufferID) {
        return (IRenderBuffer<T>) buffers.computeIfAbsent(bufferID, this::newRenderBuffer);
    }

    protected <T extends IRenderBufferLayout> IRenderBuffer<T> newRenderBuffer(@NonNull IRenderBufferID<T> bufferID) {
        return new RenderBuffer<>(bufferID, newByteBuffer(bufferID.byteSize()));
    }

    protected ByteBuffer newByteBuffer(int byteSize) {
        return ByteBuffer.allocateDirect(byteSize).order(ByteOrder.nativeOrder());
    }

    @Override
    public void render(@NonNull IRenderBufferID<?> bufferID) {
        adapt(buffers.get(bufferID));//TODO: null check, with registration error
    }

    @SuppressWarnings("unchecked")
    protected void adapt(@NonNull IRenderBuffer<?> buffer) {
        val bufferLayout = buffer.bufferLayout();
        if (bufferLayout instanceof BasicRenderBufferLayout)
            renderBuffer((IRenderBuffer<BasicRenderBufferLayout>) buffer);
    }

    protected void renderBuffer(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationItemsTexture);
        Tessellator.instance.startDrawing(GL11.GL_TRIANGLES);
        addVertices(buffer);
//        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Tessellator.instance.draw();
//        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    protected void addVertices(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer) {
        val layout = buffer.bufferLayout();
        val vertexCount = buffer.vertexCount();

        for (var i = 0; i < vertexCount; i++) {
            Tessellator.instance.setNormal(
                    layout.getNormalX(buffer, i),
                    layout.getNormalY(buffer, i),
                    layout.getNormalZ(buffer, i));
//            Tessellator.instance.setColorRGBA_F(
//                    layout.getColorR(buffer, i),
//                    layout.getColorG(buffer, i),
//                    layout.getColorB(buffer, i),
//                    layout.getColorA(buffer, i));
            Tessellator.instance.setTextureUV(
                    layout.getTextureU(buffer, i),
                    layout.getTextureV(buffer, i));
            Tessellator.instance.addVertex(
                    layout.getPositionX(buffer, i),
                    layout.getPositionY(buffer, i),
                    layout.getPositionZ(buffer, i));
        }
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
