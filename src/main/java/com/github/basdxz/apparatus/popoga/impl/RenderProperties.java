package com.github.basdxz.apparatus.popoga.impl;

import com.github.basdxz.apparatus.popoga.IRenderProperties;
import lombok.*;
import lombok.experimental.*;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;

import java.awt.*;

@Getter
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class RenderProperties implements IRenderProperties {
    protected final boolean hasAlpha;

    protected final boolean hasNormal;

    protected final boolean hasLighting;

    protected final Color color;

    protected final Vector3fc translation;

    protected final Quaternionfc rotation;

    protected final Vector3fc scale;
}
