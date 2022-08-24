package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.*;
import lombok.*;
import lombok.experimental.*;
import org.joml.Matrix3f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class TestRenderModel implements IRenderModel<TestRenderModel.TestRenderModelInstance> {
    public static TestRenderModel INSTANCE = new TestRenderModel();

    protected final static int VERTICES_PER_FACE = 3;
    protected final static BasicRenderBufferLayout BUFFER_LAYOUT = BasicRenderBufferLayout.INSTANCE;
    protected final static IRenderBufferInfo<BasicRenderBufferLayout> BUFFER_INFO =
            new RenderBufferInfo<>(BUFFER_LAYOUT, INSTANCE.faces.size() * VERTICES_PER_FACE);

    protected final List<IFace> faces = newSquare();

    public static List<IFace> newSquare() {
        val faces = new ArrayList<IFace>();

        val normal = new Vector3f(0F, 0F, 1F);
        val color = new Vector4f(1F, 0F, 0F, 1F);

        val vertA = new Vertex(new Vector3f(0F, 0F, 0F),
                               normal,
                               color,
                               new Vector2f(0F, 0F));
        val vertB = new Vertex(new Vector3f(1F, 0F, 0F),
                               normal,
                               color,
                               new Vector2f(1F, 0F));
        val vertC = new Vertex(new Vector3f(1F, 1F, 0F),
                               normal,
                               color,
                               new Vector2f(1F, 1F));
        val vertD = new Vertex(new Vector3f(0F, 1F, 0F),
                               normal,
                               color,
                               new Vector2f(0F, 1F));

        val faceA = new Face(vertA, vertB, vertC);
        val faceB = new Face(vertA, vertC, vertD);

        faces.add(faceA);
        faces.add(faceB);

        return faces;
    }

    @Override
    public TestRenderModelInstance newModelInstance() {
        return new TestRenderModelInstance();
    }

    protected int vertexIndex(int faceIndex, int faceVertexIndex) {
        return faceIndex * 3 + faceVertexIndex;
    }

    @Getter
    @Accessors(fluent = true, chain = true)
    protected class TestRenderModelInstance implements IRenderModelInstance {
        protected final IRenderBufferID<BasicRenderBufferLayout> bufferID = newRenderBufferID();
        protected final Vector4f color = new Vector4f();
        protected final Matrix3f textureTransform = new Matrix3f();

        @Override
        public void render(@NonNull IRenderContext context) {
            putModel(context.getRenderBuffer(bufferID), this);
            context.render(bufferID);
        }
    }

    protected IRenderBufferID<BasicRenderBufferLayout> newRenderBufferID() {
        return new RenderBufferID<>(BUFFER_INFO, "test_buffer");
    }

    protected void putModel(@NonNull IRenderBuffer<BasicRenderBufferLayout> buffer,
                            @NonNull TestRenderModel.TestRenderModelInstance instance) {
        val bufferLayout = buffer.bufferLayout();
        val faceCount = faces.size();
        for (int i = 0; i < faceCount; i++) {
            val face = faces.get(i);
            for (int j = 0; j < 3; j++) {
                val vertex = face.verts().get(j);
                val vertexIndex = vertexIndex(i, j);
                val tempTexture = new Vector3f(vertex.texture().x(), vertex.texture().y(), 1F);
                tempTexture.mul(instance.textureTransform);
                val texture = new Vector2f(tempTexture.x(), tempTexture.y());
                bufferLayout.putPosition(buffer, vertexIndex, vertex.position())
                            .putNormal(buffer, vertexIndex, vertex.normal())
                            .putColor(buffer, vertexIndex, instance.color())
                            .putTexture(buffer, vertexIndex, texture);
            }
        }
    }
}
