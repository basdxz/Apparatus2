package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IFace;
import com.github.basdxz.apparatus.common.render.IVertex;
import lombok.*;
import lombok.experimental.*;

import java.util.Arrays;
import java.util.List;

@Data
@Accessors(fluent = true, chain = true)
public class Face implements IFace {
    protected final List<IVertex> verts;
    protected final IVertex vertA;
    protected final IVertex vertB;
    protected final IVertex vertC;

    public Face(@NonNull IVertex vertA, @NonNull IVertex vertB, @NonNull IVertex vertC) {
        verts = Arrays.asList(vertA, vertB, vertC);
        this.vertA = vertA;
        this.vertB = vertB;
        this.vertC = vertC;
    }
}
