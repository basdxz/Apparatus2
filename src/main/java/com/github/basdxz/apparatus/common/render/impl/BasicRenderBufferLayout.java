package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBufferLayout;

public class BasicRenderBufferLayout implements IRenderBufferLayout {
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

    public final static BasicRenderBufferLayout INSTANCE = new BasicRenderBufferLayout();

    @Override
    public String layoutName() {
        return BASIC_RENDER_BUFFER_LAYOUT_NAME;
    }

    @Override
    public int vertexByteSize() {
        return VERTEX_BYTE_SIZE;
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
        return VERTEX_BYTE_SIZE * index;
    }
}
