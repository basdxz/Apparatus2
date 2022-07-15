package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.*;
import lombok.*;
import lombok.experimental.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static com.github.basdxz.apparatus.common.render.BufferedModelUtil.*;

public class TestRenderModel implements IRenderModel<TestRenderModel.TestRenderModelInfo> {
    public static TestRenderModel INSTANCE = new TestRenderModel();

    protected static int VERTICES_PER_FACE = 3;
    protected static int FLOAT_SIZE_BYTES = 4;

    public static int requiredBufferByteSize() {
        return TestModelOld.INSTANCE.faces().size() * VERTICES_PER_FACE * VERTEX_FLOAT_SIZE * FLOAT_SIZE_BYTES;
    }

    protected final List<IFace> faces = newSquare();

    public static List<IFace> newSquare() {
        val faces = new ArrayList<IFace>();

        val normal = new Vector3f();
        val color = new Vector4f(1F, 0F, 0F, 1F);
        val texture = new Vector2f();

        val vertA = new Vertex(new Vector3f(0F, 0F, 0F),
                               normal,
                               color,
                               texture);
        val vertB = new Vertex(new Vector3f(1F, 0F, 0F),
                               normal,
                               color,
                               texture);
        val vertC = new Vertex(new Vector3f(1F, 1F, 0F),
                               normal,
                               color,
                               texture);
        val vertD = new Vertex(new Vector3f(0F, 1F, 0F),
                               normal,
                               color,
                               texture);

        val faceA = new Face(vertA, vertB, vertC);
        val faceB = new Face(vertA, vertC, vertD);

        faces.add(faceA);
        faces.add(faceB);

        return faces;
    }

    @Override
    public TestRenderModelInfo newModelInfo() {
        return new TestRenderModelInfo();
    }

    @Override
    public IBufferedModel bufferModel(@NonNull IRenderBuffer renderBuffer, @NonNull TestRenderModelInfo modelInfo) {
        val floatBuffer = renderBuffer.byteBuffer().asFloatBuffer();
        copyModel(floatBuffer, modelInfo);
        return new BufferedModel(renderBuffer, floatBuffer);
    }

    protected void copyModel(@NonNull FloatBuffer floatBuffer, @NonNull TestRenderModelInfo modelInfo) {
        val faceCount = faces.size();
        for (int i = 0; i < faceCount; i++) {
            val face = faces.get(i);
            for (int j = 0; j < 3; j++) {
                val vertex = face.verts().get(j);
                val vertexIndex = vertexIndex(i, j);
                vertex.position().get(floatPositionOffset(vertexIndex), floatBuffer);
                vertex.normal().get(floatNormalOffset(vertexIndex), floatBuffer);
                modelInfo.color().get(floatColorOffset(vertexIndex), floatBuffer);
                vertex.texture().get(floatTextureOffset(vertexIndex), floatBuffer);
            }
        }
    }

    protected int vertexIndex(int faceIndex, int faceVertexIndex) {
        return faceIndex * 3 + faceVertexIndex;
    }

    @Getter
    @Accessors(fluent = true, chain = true)
    protected static class TestRenderModelInfo implements IRenderModelInfo {
        protected final Vector4f color = new Vector4f();
    }
}