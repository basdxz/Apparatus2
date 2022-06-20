package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resource.IMeshResource;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.resource.impl.ResourceType.MESH;

@Data
@Accessors(fluent = true, chain = true)
public class MeshResource implements IMeshResource {
    protected final ILocation location;

    @Override
    public IResourceType type() {
        return MESH;
    }
}
