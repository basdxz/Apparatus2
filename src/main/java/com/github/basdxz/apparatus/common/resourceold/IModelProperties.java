package com.github.basdxz.apparatus.common.resourceold;

import org.joml.Quaternionfc;
import org.joml.Vector3fc;

import java.awt.*;

@Deprecated
public interface IModelProperties extends IResourceOld {
    boolean hasAlpha();

    boolean hasNormal();

    boolean hasLighting();

    Color color();

    Vector3fc translation();

    Quaternionfc rotation();

    Vector3fc scale();
}
