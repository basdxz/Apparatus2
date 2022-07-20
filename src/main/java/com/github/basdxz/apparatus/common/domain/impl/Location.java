package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class Location<RESOURCE_TYPE extends IResourceType> implements ILocation<RESOURCE_TYPE> {
    protected final IDomain domain;
    protected final String path;
    protected final RESOURCE_TYPE resourceType;

    protected Location(@NonNull IDomain domain, @NonNull String path, @NonNull RESOURCE_TYPE resourceType) {
        this.domain = domain;
        this.path = path.intern();
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        return locationToString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ILocation))
            return false;
        return locationEquals((ILocation) obj);
    }
}
