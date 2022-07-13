package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.BufferedModelUtil;
import com.github.basdxz.apparatus.common.render.IBufferedModel;
import com.github.basdxz.apparatus.common.render.IModel;
import lombok.*;
import lombok.experimental.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static com.github.basdxz.apparatus.common.render.BufferedModelUtil.VERTEX_FLOAT_SIZE;

@Data
@Accessors(fluent = true, chain = true)
public class TestBufferedModel implements IBufferedModel {
    protected final FloatBuffer floatBuffer;

    public TestBufferedModel(@NonNull ByteBuffer byteBuffer, @NonNull IModel model) {
        this.floatBuffer = byteBuffer.asFloatBuffer();

        if (floatBuffer.capacity() < model.faces().size() * 3 * VERTEX_FLOAT_SIZE)
            throw new RuntimeException("buffer too small lmao"); //TODO: better exceptions

        copyModel(model);
    }

    protected void copyModel(@NonNull IModel model) {
        val faceCount = model.faces().size();
        for (int i = 0; i < faceCount; i++) {
            val face = model.faces().get(i);
            for (int j = 0; j < 3; j++) {
                val vertex = face.verts().get(j);
                val vertexIndex = vertexIndex(i, j);
                vertex.position().get(BufferedModelUtil.floatPositionOffset(vertexIndex), floatBuffer);
                vertex.normal().get(BufferedModelUtil.floatNormalOffset(vertexIndex), floatBuffer);
                vertex.color().get(BufferedModelUtil.floatColorOffset(vertexIndex), floatBuffer);
                vertex.texture().get(BufferedModelUtil.floatTextureOffset(vertexIndex), floatBuffer);
            }
        }
    }

    protected int vertexIndex(int faceIndex, int faceVertexIndex) {
        return faceIndex * 3 + faceVertexIndex;
    }
}
