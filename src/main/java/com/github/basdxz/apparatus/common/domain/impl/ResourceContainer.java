package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.domain.IResourceContainer;
import com.github.basdxz.apparatus.common.resource.IResource;
import lombok.*;
import lombok.experimental.*;

import static lombok.AccessLevel.PROTECTED;

@Accessors(fluent = true, chain = true)
@RequiredArgsConstructor(access = PROTECTED)
public class ResourceContainer<RESOURCE extends IResource> implements IResourceContainer<RESOURCE> {
    @Getter
    @NonNull
    protected final ILocation<RESOURCE> location;
    protected RESOURCE resource = null;

    @Override
    public void reset() {
        resource = null;
    }

    @Override
    public ResourceContainer<RESOURCE> resource(@NonNull RESOURCE resource) {
//        ensureSameLocation(resource); //TODO: fix location
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
