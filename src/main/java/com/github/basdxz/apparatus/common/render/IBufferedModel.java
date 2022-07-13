package com.github.basdxz.apparatus.common.render;

import lombok.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.nio.FloatBuffer;

import static com.github.basdxz.apparatus.common.render.BufferedModelUtil.*;

public interface IBufferedModel {
    default int vertexCount() {
        return floatBuffer().capacity() / VERTEX_FLOAT_SIZE;
    }

    default Vector3f position(int index) {
        return position(index, new Vector3f());
    }

    default Vector3f position(int index, @NonNull Vector3f output) {
        return output.set(positionX(index), positionY(index), positionZ(index));
    }

    default float positionX(int index) {
        return floatBuffer().get(floatPositionXIndex(index));
    }

    default float positionY(int index) {
        return floatBuffer().get(floatPositionYIndex(index));
    }

    default float positionZ(int index) {
        return floatBuffer().get(floatPositionZIndex(index));
    }

    default Vector3f normal(int index) {
        return normal(index, new Vector3f());
    }

    default Vector3f normal(int index, @NonNull Vector3f output) {
        return output.set(normalX(index), normalY(index), normalZ(index));
    }

    default float normalX(int index) {
        return floatBuffer().get(floatNormalXIndex(index));
    }

    default float normalY(int index) {
        return floatBuffer().get(floatNormalYIndex(index));
    }

    default float normalZ(int index) {
        return floatBuffer().get(floatNormalZIndex(index));
    }

    default Vector4f color(int index) {
        return color(index, new Vector4f());
    }

    default Vector4f color(int index, @NonNull Vector4f output) {
        return output.set(colorR(index), colorG(index), colorB(index), colorA(index));
    }

    default float colorR(int index) {
        return floatBuffer().get(floatColorRIndex(index));
    }

    default float colorG(int index) {
        return floatBuffer().get(floatColorGIndex(index));
    }

    default float colorB(int index) {
        return floatBuffer().get(floatColorBIndex(index));
    }

    default float colorA(int index) {
        return floatBuffer().get(floatColorAIndex(index));
    }

    default Vector2f texture(int index) {
        return texture(index, new Vector2f());
    }

    default Vector2f texture(int index, @NonNull Vector2f output) {
        return output.set(textureU(index), textureV(index));
    }

    default float textureU(int index) {
        return floatBuffer().get(floatTextureUIndex(index));
    }

    default float textureV(int index) {
        return floatBuffer().get(floatTextureVIndex(index));
    }

    FloatBuffer floatBuffer();
}
