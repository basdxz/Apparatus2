package com.github.basdxz.apparatus.popoga;

import org.joml.Quaternionfc;
import org.joml.Vector3fc;

import java.awt.*;

public interface IRenderProperties {
    boolean hasAlpha();

    boolean hasNormal();

    boolean hasLighting();

    Color color();

    Vector3fc translation();

    Quaternionfc rotation();

    Vector3fc scale();
}
