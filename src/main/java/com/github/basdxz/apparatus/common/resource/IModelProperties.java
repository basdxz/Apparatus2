package com.github.basdxz.apparatus.common.resource;

import org.joml.Quaternionfc;
import org.joml.Vector3fc;

import java.awt.*;

//TODO: This should not be an IResource but two separate things, the ModelProperties and it's Resource?
public interface IModelProperties extends IResource {
    boolean hasAlpha();

    boolean hasNormal();

    boolean hasLighting();

    Color color();

    Vector3fc translation();

    Quaternionfc rotation();

    Vector3fc scale();
}
