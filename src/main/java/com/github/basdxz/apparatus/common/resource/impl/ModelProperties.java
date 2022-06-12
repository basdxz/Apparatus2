package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IModelProperties;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;
import lombok.experimental.*;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;

import java.awt.*;

import static com.github.basdxz.apparatus.common.resource.impl.ResourceType.MODEL_PROPERTIES;

@Getter
@AllArgsConstructor
@Accessors(fluent = true, chain = true)
public class ModelProperties implements IModelProperties {
    protected final String domain;
    protected final String location;
    protected final boolean hasAlpha;
    protected final boolean hasNormal;
    protected final boolean hasLighting;
    protected final Color color;
    protected final Vector3fc translation;
    protected final Quaternionfc rotation;
    protected final Vector3fc scale;

    @Override
    public IResourceType type() {
        return MODEL_PROPERTIES;
    }
}
