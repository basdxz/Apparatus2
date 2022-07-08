package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resourceold.IMeshResource;
import com.github.basdxz.apparatus.common.resourceold.IResourceType;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resourceold.impl.ResourceType.MESH;

@Data
@Accessors(fluent = true, chain = true)
public class MeshResource implements IMeshResource {
    protected final ILocation location;

    @Override
    public IResourceType type() {
        return MESH;
    }
}
