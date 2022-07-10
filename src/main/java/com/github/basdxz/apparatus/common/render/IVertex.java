package com.github.basdxz.apparatus.common.render;

import org.joml.Vector2fc;
import org.joml.Vector3fc;
import org.joml.Vector4fc;

public interface IVertex {
    Vector3fc position();

    Vector3fc normal();

    Vector4fc color();

    Vector2fc texture();
}
