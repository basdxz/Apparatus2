package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IFace;
import com.github.basdxz.apparatus.common.render.IModelOld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.basdxz.apparatus.common.render.impl.TestRenderModel.newSquare;

public class TestModelOld implements IModelOld {
    protected static int VERTICES_PER_FACE = 3;
    protected static int FLOAT_SIZE_BYTES = 4;

    public static IModelOld INSTANCE = new TestModelOld();

    protected final List<IFace> faces = new ArrayList<>();

    public TestModelOld() {
        faces.addAll(newSquare());
    }

    @Override
    public List<IFace> faces() {
        return Collections.unmodifiableList(faces);
    }
}
