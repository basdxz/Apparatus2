package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resource.IResource;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class Location<RESOURCE extends IResource> implements ILocation<RESOURCE> {
    protected final IDomain domain;
    protected final String path;
    protected final IResourceType<RESOURCE> resourceType;

    protected Location(@NonNull IDomain domain, @NonNull String path, @NonNull IResourceType<RESOURCE> resourceType) {
        this.domain = domain;
        this.path = path.intern();
        this.resourceType = resourceType;
    }
}
