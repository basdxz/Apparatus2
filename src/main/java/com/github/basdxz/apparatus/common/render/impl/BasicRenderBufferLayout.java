package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBuffer;
import com.github.basdxz.apparatus.common.render.IRenderBufferLayout;
import lombok.*;
import org.joml.*;

public class BasicRenderBufferLayout implements IRenderBufferLayout {
    public final static BasicRenderBufferLayout INSTANCE = new BasicRenderBufferLayout();

    public final static String BASIC_RENDER_BUFFER_LAYOUT_NAME = "basic_render_buffer_layout";

    public final static int POSITION_X_INDEX = 0;
    public final static int POSITION_Y_INDEX = 1;
    public final static int POSITION_Z_INDEX = 2;
    public final static int POSITION_FLOAT_SIZE = 3;
    public final static int POSITION_FLOAT_OFFSET = 0;

    public final static int NORMAL_X_INDEX = 0;
    public final static int NORMAL_Y_INDEX = 1;
    public final static int NORMAL_Z_INDEX = 2;
    public final static int NORMAL_FLOAT_SIZE = 3;
    public final static int NORMAL_FLOAT_OFFSET = POSITION_FLOAT_SIZE + POSITION_FLOAT_OFFSET;

    public final static int COLOR_R_INDEX = 0;
    public final static int COLOR_G_INDEX = 1;
    public final static int COLOR_B_INDEX = 2;
    public final static int COLOR_A_INDEX = 3;
    public final static int COLOR_FLOAT_SIZE = 4;
    public final static int COLOR_FLOAT_OFFSET = NORMAL_FLOAT_SIZE + NORMAL_FLOAT_OFFSET;

    public final static int TEXTURE_U_INDEX = 0;
    public final static int TEXTURE_V_INDEX = 1;
    public final static int TEXTURE_FLOAT_SIZE = 2;
    public final static int TEXTURE_FLOAT_OFFSET = COLOR_FLOAT_SIZE + COLOR_FLOAT_OFFSET;

    public final static int VERTEX_FLOAT_SIZE = TEXTURE_FLOAT_SIZE + TEXTURE_FLOAT_OFFSET;
    public final static int VERTEX_BYTE_SIZE = VERTEX_FLOAT_SIZE * Float.BYTES;

    @Override
    public String layoutName() {
        return BASIC_RENDER_BUFFER_LAYOUT_NAME;
    }

    @Override
    public int vertexByteSize() {
        return VERTEX_BYTE_SIZE;
    }

    public BasicRenderBufferLayout putPosition(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                                               int index,
                                               @NonNull Vector3fc position) {
        position.get(floatPositionOffset(buffer, index), buffer.floatBuffer());
        return this;
    }

    public void putPositionX(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float positionX) {
        buffer.floatBuffer().put(floatPositionXIndex(buffer, index), positionX);
    }

    public void putPositionY(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float positionY) {
        buffer.floatBuffer().put(floatPositionYIndex(buffer, index), positionY);
    }

    public void putPositionZ(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float positionZ) {
        buffer.floatBuffer().put(floatPositionZIndex(buffer, index), positionZ);
    }

    public Vector3f getPosition(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return getPosition(buffer, index, newPositionOutput());
    }

    public Vector3f newPositionOutput() {
        return new Vector3f();
    }

    public Vector3f getPosition(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                                int index,
                                @NonNull Vector3f output) {
        return output.set(getPositionX(buffer, index), getPositionY(buffer, index), getPositionZ(buffer, index));
    }

    public float getPositionX(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatPositionXIndex(buffer, index));
    }

    public float getPositionY(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatPositionYIndex(buffer, index));
    }

    public float getPositionZ(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatPositionZIndex(buffer, index));
    }

    public int floatPositionXIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatPositionOffset(buffer, index) + POSITION_X_INDEX;
    }

    public int floatPositionYIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatPositionOffset(buffer, index) + POSITION_Y_INDEX;
    }

    public int floatPositionZIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatPositionOffset(buffer, index) + POSITION_Z_INDEX;
    }

    public int floatPositionOffset(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatVertexOffset(buffer, index) + POSITION_FLOAT_OFFSET;
    }

    public BasicRenderBufferLayout putNormal(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                                             int index,
                                             @NonNull Vector3fc normal) {
        normal.get(floatNormalOffset(buffer, index), buffer.floatBuffer());
        return this;
    }

    public void putNormalX(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float normalX) {
        buffer.floatBuffer().put(floatNormalXIndex(buffer, index), normalX);
    }

    public void putNormalY(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float normalY) {
        buffer.floatBuffer().put(floatNormalYIndex(buffer, index), normalY);
    }

    public void putNormalZ(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float normalZ) {
        buffer.floatBuffer().put(floatNormalZIndex(buffer, index), normalZ);
    }

    public Vector3f getNormal(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return getNormal(buffer, index, newNormalOutput());
    }

    public Vector3f newNormalOutput() {
        return new Vector3f();
    }

    public Vector3f getNormal(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                              int index,
                              @NonNull Vector3f output) {
        return output.set(getNormalX(buffer, index), getNormalY(buffer, index), getNormalZ(buffer, index));
    }

    public float getNormalX(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatNormalXIndex(buffer, index));
    }

    public float getNormalY(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatNormalYIndex(buffer, index));
    }

    public float getNormalZ(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatNormalZIndex(buffer, index));
    }

    public int floatNormalXIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatNormalOffset(buffer, index) + NORMAL_X_INDEX;
    }

    public int floatNormalYIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatNormalOffset(buffer, index) + NORMAL_Y_INDEX;
    }

    public int floatNormalZIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatNormalOffset(buffer, index) + NORMAL_Z_INDEX;
    }

    public int floatNormalOffset(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatVertexOffset(buffer, index) + NORMAL_FLOAT_OFFSET;
    }

    public BasicRenderBufferLayout putColor(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                                            int index,
                                            @NonNull Vector4fc color) {
        color.get(floatColorOffset(buffer, index), buffer.floatBuffer());
        return this;
    }

    public void putColorR(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float colorR) {
        buffer.floatBuffer().put(floatColorRIndex(buffer, index), colorR);
    }

    public void putColorG(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float colorG) {
        buffer.floatBuffer().put(floatColorGIndex(buffer, index), colorG);
    }

    public void putColorB(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float colorB) {
        buffer.floatBuffer().put(floatColorBIndex(buffer, index), colorB);
    }

    public void putColorA(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float colorA) {
        buffer.floatBuffer().put(floatColorAIndex(buffer, index), colorA);
    }

    public Vector4f getColor(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return getColor(buffer, index, newColorOutput());
    }

    public Vector4f newColorOutput() {
        return new Vector4f();
    }

    public Vector4f getColor(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                             int index,
                             @NonNull Vector4f output) {
        return output.set(getColorR(buffer, index),
                          getColorG(buffer, index),
                          getColorB(buffer, index),
                          getColorA(buffer, index));
    }

    public float getColorR(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatColorRIndex(buffer, index));
    }

    public float getColorG(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatColorGIndex(buffer, index));
    }

    public float getColorB(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatColorBIndex(buffer, index));
    }

    public float getColorA(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatColorAIndex(buffer, index));
    }

    public int floatColorRIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatColorOffset(buffer, index) + COLOR_R_INDEX;
    }

    public int floatColorGIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatColorOffset(buffer, index) + COLOR_G_INDEX;
    }

    public int floatColorBIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatColorOffset(buffer, index) + COLOR_B_INDEX;
    }

    public int floatColorAIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatColorOffset(buffer, index) + COLOR_A_INDEX;
    }

    public int floatColorOffset(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatVertexOffset(buffer, index) + COLOR_FLOAT_OFFSET;
    }

    public BasicRenderBufferLayout putTexture(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                                              int index,
                                              @NonNull Vector2fc texture) {
        texture.get(floatTextureOffset(buffer, index), buffer.floatBuffer());
        return this;
    }

    public void putTextureU(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float textureV) {
        buffer.floatBuffer().put(floatTextureUIndex(buffer, index), textureV);
    }

    public void putTextureV(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float textureU) {
        buffer.floatBuffer().put(floatTextureVIndex(buffer, index), textureU);
    }

    public Vector2f getTexture(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return getTexture(buffer, index, newTextureOutput());
    }

    public Vector2f newTextureOutput() {
        return new Vector2f();
    }

    public Vector2f getTexture(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                               int index,
                               @NonNull Vector2f output) {
        return output.set(getTextureU(buffer, index), getTextureV(buffer, index));
    }

    public float getTextureU(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatTextureUIndex(buffer, index));
    }

    public float getTextureV(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatTextureVIndex(buffer, index));
    }

    public int floatTextureUIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatTextureOffset(buffer, index) + TEXTURE_U_INDEX;
    }

    public int floatTextureVIndex(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatTextureOffset(buffer, index) + TEXTURE_V_INDEX;
    }

    public int floatTextureOffset(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return floatVertexOffset(buffer, index) + TEXTURE_FLOAT_OFFSET;
    }

    public int floatVertexOffset(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        buffer.ensureIndexBounds(index);
        return VERTEX_FLOAT_SIZE * index;
    }
}
