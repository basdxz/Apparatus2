package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resourceold.IModelProperties;
import com.github.basdxz.apparatus.common.resourceold.IResourceTypeOld;
import lombok.*;
import lombok.experimental.*;
import org.joml.Quaternionfc;
import org.joml.Vector3fc;

import java.awt.*;

import static com.github.basdxz.apparatus.common.resourceold.impl.ResourceTypeOld.MODEL_PROPERTIES;

@Data
@Deprecated
@Accessors(fluent = true, chain = true)
public class ModelPropertiesOld implements IModelProperties {
    @NonNull
    protected final ILocation location;
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

//    public static IModelProperties newDefaultProperties(@NonNull IDomain domain, @NonNull String path) {
//        return new ModelPropertiesOld(
//                domain.location(path),
//                true,
//                true,
//                true,
//                Color.WHITE,
//                new Vector3f(),
//                new Quaternionf(),
//                new Vector3f(1F, 1F, 1F)
//        );
//    }

    @Override
    public IResourceTypeOld type() {
        return MODEL_PROPERTIES;
    }
}
