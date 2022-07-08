package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resourceold.IMeshResource;
import com.github.basdxz.apparatus.common.resourceold.IResourceTypeOld;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resourceold.impl.ResourceTypeOld.MESH;

@Data
@Deprecated
@Accessors(fluent = true, chain = true)
public class MeshResourceOld implements IMeshResource {
    protected final ILocation location;

    @Override
    public IResourceTypeOld type() {
        return MESH;
    }
}
