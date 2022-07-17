package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBuffer;
import com.github.basdxz.apparatus.common.render.IRenderBufferLayout;
import lombok.*;
import org.joml.*;

public class BasicRenderBufferLayout implements IRenderBufferLayout {
    public final static BasicRenderBufferLayout INSTANCE = new BasicRenderBufferLayout();

    protected final static String BASIC_RENDER_BUFFER_LAYOUT_NAME = "basic_render_buffer_layout";

    private final static int POSITION_X_INDEX = 0;
    private final static int POSITION_Y_INDEX = 1;
    private final static int POSITION_Z_INDEX = 2;
    private final static int POSITION_FLOAT_SIZE = 3;
    private final static int POSITION_FLOAT_OFFSET = 0;

    private final static int NORMAL_X_INDEX = 0;
    private final static int NORMAL_Y_INDEX = 1;
    private final static int NORMAL_Z_INDEX = 2;
    private final static int NORMAL_FLOAT_SIZE = 3;
    private final static int NORMAL_FLOAT_OFFSET = POSITION_FLOAT_SIZE + POSITION_FLOAT_OFFSET;

    private final static int COLOR_R_INDEX = 0;
    private final static int COLOR_G_INDEX = 1;
    private final static int COLOR_B_INDEX = 2;
    private final static int COLOR_A_INDEX = 3;
    private final static int COLOR_FLOAT_SIZE = 4;
    private final static int COLOR_FLOAT_OFFSET = NORMAL_FLOAT_SIZE + NORMAL_FLOAT_OFFSET;

    private final static int TEXTURE_U_INDEX = 0;
    private final static int TEXTURE_V_INDEX = 1;
    private final static int TEXTURE_FLOAT_SIZE = 2;
    private final static int TEXTURE_FLOAT_OFFSET = COLOR_FLOAT_SIZE + COLOR_FLOAT_OFFSET;

    private final static int VERTEX_FLOAT_SIZE = TEXTURE_FLOAT_SIZE + TEXTURE_FLOAT_OFFSET;
    private final static int VERTEX_BYTE_SIZE = VERTEX_FLOAT_SIZE * Float.BYTES;

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
        putPositionX(buffer, index, position.x());
        putPositionY(buffer, index, position.y());
        putPositionZ(buffer, index, position.z());
        return this;
    }

    public void putPositionX(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float positionX) {
        buffer.floatBuffer().put(floatPositionXIndex(index), positionX);
    }

    public void putPositionY(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float positionY) {
        buffer.floatBuffer().put(floatPositionYIndex(index), positionY);
    }

    public void putPositionZ(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float positionZ) {
        buffer.floatBuffer().put(floatPositionZIndex(index), positionZ);
    }

    public Vector3f getPosition(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return getPosition(buffer, index, newPositionOutput());
    }

    private Vector3f newPositionOutput() {
        return new Vector3f();
    }

    public Vector3f getPosition(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                                int index,
                                @NonNull Vector3f output) {
        return output.set(getPositionX(buffer, index),
                          getPositionY(buffer, index),
                          getPositionZ(buffer, index));
    }

    public float getPositionX(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatPositionXIndex(index));
    }

    public float getPositionY(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatPositionYIndex(index));
    }

    public float getPositionZ(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatPositionZIndex(index));
    }

    private static int floatPositionXIndex(int index) {
        return floatPositionOffset(index) + POSITION_X_INDEX;
    }

    private static int floatPositionYIndex(int index) {
        return floatPositionOffset(index) + POSITION_Y_INDEX;
    }

    private static int floatPositionZIndex(int index) {
        return floatPositionOffset(index) + POSITION_Z_INDEX;
    }

    private static int floatPositionOffset(int index) {
        return floatVertexOffset(index) + POSITION_FLOAT_OFFSET;
    }

    public BasicRenderBufferLayout putNormal(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                                             int index,
                                             @NonNull Vector3fc normal) {
        putNormalX(buffer, index, normal.x());
        putNormalY(buffer, index, normal.y());
        putNormalZ(buffer, index, normal.z());
        return this;
    }

    public void putNormalX(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float normalX) {
        buffer.floatBuffer().put(floatNormalXIndex(index), normalX);
    }

    public void putNormalY(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float normalY) {
        buffer.floatBuffer().put(floatNormalYIndex(index), normalY);
    }

    public void putNormalZ(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float normalZ) {
        buffer.floatBuffer().put(floatNormalZIndex(index), normalZ);
    }

    public Vector3f getNormal(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return getNormal(buffer, index, newNormalOutput());
    }

    private Vector3f newNormalOutput() {
        return new Vector3f();
    }

    public Vector3f getNormal(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                              int index,
                              @NonNull Vector3f output) {
        return output.set(getNormalX(buffer, index),
                          getNormalY(buffer, index),
                          getNormalZ(buffer, index));
    }

    public float getNormalX(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatNormalXIndex(index));
    }

    public float getNormalY(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatNormalYIndex(index));
    }

    public float getNormalZ(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatNormalZIndex(index));
    }

    private static int floatNormalXIndex(int index) {
        return floatNormalOffset(index) + NORMAL_X_INDEX;
    }

    private static int floatNormalYIndex(int index) {
        return floatNormalOffset(index) + NORMAL_Y_INDEX;
    }

    private static int floatNormalZIndex(int index) {
        return floatNormalOffset(index) + NORMAL_Z_INDEX;
    }

    private static int floatNormalOffset(int index) {
        return floatVertexOffset(index) + NORMAL_FLOAT_OFFSET;
    }

    public BasicRenderBufferLayout putColor(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                                            int index,
                                            @NonNull Vector4fc color) {
        putColorR(buffer, index, color.x());
        putColorG(buffer, index, color.y());
        putColorB(buffer, index, color.z());
        putColorA(buffer, index, color.w());
        return this;
    }

    public void putColorR(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float colorR) {
        buffer.floatBuffer().put(floatColorRIndex(index), colorR);
    }

    public void putColorG(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float colorG) {
        buffer.floatBuffer().put(floatColorGIndex(index), colorG);
    }

    public void putColorB(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float colorB) {
        buffer.floatBuffer().put(floatColorBIndex(index), colorB);
    }

    public void putColorA(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float colorA) {
        buffer.floatBuffer().put(floatColorAIndex(index), colorA);
    }

    public Vector4f getColor(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return getColor(buffer, index, newColorOutput());
    }

    private Vector4f newColorOutput() {
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
        return buffer.floatBuffer().get(floatColorRIndex(index));
    }

    public float getColorG(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatColorGIndex(index));
    }

    public float getColorB(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatColorBIndex(index));
    }

    public float getColorA(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatColorAIndex(index));
    }

    private static int floatColorRIndex(int index) {
        return floatColorOffset(index) + COLOR_R_INDEX;
    }

    private static int floatColorGIndex(int index) {
        return floatColorOffset(index) + COLOR_G_INDEX;
    }

    private static int floatColorBIndex(int index) {
        return floatColorOffset(index) + COLOR_B_INDEX;
    }

    private static int floatColorAIndex(int index) {
        return floatColorOffset(index) + COLOR_A_INDEX;
    }

    private static int floatColorOffset(int index) {
        return floatVertexOffset(index) + COLOR_FLOAT_OFFSET;
    }

    public BasicRenderBufferLayout putTexture(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                                              int index,
                                              @NonNull Vector2fc texture) {
        putTextureU(buffer, index, texture.x());
        putTextureV(buffer, index, texture.y());
        return this;
    }

    public void putTextureU(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float textureV) {
        buffer.floatBuffer().put(floatTextureUIndex(index), textureV);
    }

    public void putTextureV(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index, float textureU) {
        buffer.floatBuffer().put(floatTextureVIndex(index), textureU);
    }

    public Vector2f getTexture(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return getTexture(buffer, index, newTextureOutput());
    }

    private Vector2f newTextureOutput() {
        return new Vector2f();
    }

    public Vector2f getTexture(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                               int index,
                               @NonNull Vector2f output) {
        return output.set(getTextureU(buffer, index), getTextureV(buffer, index));
    }

    public float getTextureU(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatTextureUIndex(index));
    }

    public float getTextureV(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer, int index) {
        return buffer.floatBuffer().get(floatTextureVIndex(index));
    }

    private static int floatTextureUIndex(int index) {
        return floatTextureOffset(index) + TEXTURE_U_INDEX;
    }

    private static int floatTextureVIndex(int index) {
        return floatTextureOffset(index) + TEXTURE_V_INDEX;
    }

    private static int floatTextureOffset(int index) {
        return floatVertexOffset(index) + TEXTURE_FLOAT_OFFSET;
    }

    private static int floatVertexOffset(int index) {
        return VERTEX_FLOAT_SIZE * index;
    }
}
