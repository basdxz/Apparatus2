package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.domain.IResourceContainer;
import com.github.basdxz.apparatus.common.resource.IResource;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;
import lombok.experimental.*;

import static lombok.AccessLevel.PROTECTED;


@Accessors(fluent = true, chain = true)
@RequiredArgsConstructor(access = PROTECTED)
public class ResourceContainer<RESOURCE_TYPE extends IResourceType, RESOURCE extends IResource<RESOURCE_TYPE>>
        implements IResourceContainer<RESOURCE_TYPE, RESOURCE> {
    @Getter
    protected final ILocation<RESOURCE_TYPE> location;
    protected RESOURCE resource = null;

    @Override
    public void reset() {
        resource = null;
    }

    @Override
    public ResourceContainer<RESOURCE_TYPE, RESOURCE> resource(@NonNull RESOURCE resource) {
        ensureSameLocation(resource);
        this.resource = resource;
        return this;
    }

    @Override
    public boolean isEmpty() {
        return resource == null;
    }

    @Override
    public RESOURCE resource() {
        ensureNotEmpty();
        return resource;
    }
}
