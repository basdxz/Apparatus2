package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IModelProperties;
import com.github.basdxz.apparatus.common.resource.IResourceLocation;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;
import lombok.experimental.*;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.awt.*;

import static com.github.basdxz.apparatus.common.resource.impl.ResourceType.MODEL_PROPERTIES;

@Data
@Accessors(fluent = true, chain = true)
public class ModelProperties implements IModelProperties {
    @NonNull
    protected final IResourceLocation location;
    protected final boolean hasAlpha;
    protected final boolean hasNormal;
    protected final boolean hasLighting;
    @NonNull
    protected final Color color;
    @NonNull
    protected final Vector3fc translation;
    @NonNull
    protected final Quaternionfc rotation;
    @NonNull
    protected final Vector3fc scale;

    public static IModelProperties newDefaultProperties(@NonNull String domain, @NonNull String path) {
        return new ModelProperties(
                new ResourceLocation(domain, path),
                true,
                true,
                true,
                Color.WHITE,
                new Vector3f(),
                new Quaternionf(),
                new Vector3f(1F, 1F, 1F)
        );
    }

    @Override
    public IResourceType type() {
        return MODEL_PROPERTIES;
    }
}
