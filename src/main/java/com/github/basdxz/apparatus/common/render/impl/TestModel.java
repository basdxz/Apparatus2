package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IFace;
import com.github.basdxz.apparatus.common.render.IModel;
import lombok.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestModel implements IModel {
    public static IModel INSTANCE = new TestModel();

    protected final List<IFace> faces = new ArrayList<>();

    public TestModel() {
        faces.addAll(newSquare());
    }

    protected static List<IFace> newSquare() {
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
    public List<IFace> faces() {
        return Collections.unmodifiableList(faces);
    }
}