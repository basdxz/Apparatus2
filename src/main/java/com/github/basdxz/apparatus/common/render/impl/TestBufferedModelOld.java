package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IBufferedModel;
import com.github.basdxz.apparatus.common.render.IModelOld;
import com.github.basdxz.apparatus.common.render.IRenderBuffer;
import lombok.*;
import lombok.experimental.*;

import java.nio.FloatBuffer;

import static com.github.basdxz.apparatus.common.render.BufferedModelUtil.*;

@Data
@Accessors(fluent = true, chain = true)
public class TestBufferedModelOld implements IBufferedModel {
    protected final IRenderBuffer renderBuffer;
    protected final FloatBuffer floatBuffer;

    public TestBufferedModelOld(@NonNull IRenderBuffer renderBuffer, @NonNull IModelOld model) {
        this.renderBuffer = renderBuffer;
        ensureBufferCapacity(model);
        this.floatBuffer = renderBuffer.byteBuffer().asFloatBuffer();
        copyModel(model);
    }

    protected void ensureBufferCapacity(@NonNull IModelOld model) {
        if (renderBuffer.byteSize() < model.faces().size() * 3 * VERTEX_FLOAT_SIZE * 4)
            throw new RuntimeException("buffer too small lmao"); //TODO: better exceptions
    }

    protected void copyModel(@NonNull IModelOld model) {
        val faceCount = model.faces().size();
        for (int i = 0; i < faceCount; i++) {
            val face = model.faces().get(i);
            for (int j = 0; j < 3; j++) {
                val vertex = face.verts().get(j);
                val vertexIndex = vertexIndex(i, j);
                vertex.position().get(floatPositionOffset(vertexIndex), floatBuffer);
                vertex.normal().get(floatNormalOffset(vertexIndex), floatBuffer);
                vertex.color().get(floatColorOffset(vertexIndex), floatBuffer);
                vertex.texture().get(floatTextureOffset(vertexIndex), floatBuffer);
            }
        }
    }

    protected int vertexIndex(int faceIndex, int faceVertexIndex) {
        return faceIndex * 3 + faceVertexIndex;
    }
}
