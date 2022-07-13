package com.github.basdxz.apparatus.common.render;

import lombok.experimental.*;

@UtilityClass
public final class BufferedModelUtil {
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

    public static int floatPositionXIndex(int index) {
        return floatPositionOffset(index) + POSITION_X_INDEX;
    }

    public static int floatPositionYIndex(int index) {
        return floatPositionOffset(index) + POSITION_Y_INDEX;
    }

    public static int floatPositionZIndex(int index) {
        return floatPositionOffset(index) + POSITION_Z_INDEX;
    }

    public static int floatPositionOffset(int index) {
        return floatVertexOffset(index) + POSITION_FLOAT_OFFSET;
    }

    public static int floatNormalXIndex(int index) {
        return floatNormalOffset(index) + NORMAL_X_INDEX;
    }

    public static int floatNormalYIndex(int index) {
        return floatNormalOffset(index) + NORMAL_Y_INDEX;
    }

    public static int floatNormalZIndex(int index) {
        return floatNormalOffset(index) + NORMAL_Z_INDEX;
    }

    public static int floatNormalOffset(int index) {
        return floatVertexOffset(index) + NORMAL_FLOAT_OFFSET;
    }

    public static int floatColorRIndex(int index) {
        return floatColorOffset(index) + COLOR_R_INDEX;
    }

    public static int floatColorGIndex(int index) {
        return floatColorOffset(index) + COLOR_G_INDEX;
    }

    public static int floatColorBIndex(int index) {
        return floatColorOffset(index) + COLOR_B_INDEX;
    }

    public static int floatColorAIndex(int index) {
        return floatColorOffset(index) + COLOR_A_INDEX;
    }

    public static int floatColorOffset(int index) {
        return floatVertexOffset(index) + COLOR_FLOAT_OFFSET;
    }

    public static int floatTextureUIndex(int index) {
        return floatTextureOffset(index) + TEXTURE_U_INDEX;
    }

    public static int floatTextureVIndex(int index) {
        return floatTextureOffset(index) + TEXTURE_V_INDEX;
    }

    public static int floatTextureOffset(int index) {
        return floatVertexOffset(index) + TEXTURE_FLOAT_OFFSET;
    }

    public static int floatVertexOffset(int index) {
        return VERTEX_FLOAT_SIZE * index;
    }
}
