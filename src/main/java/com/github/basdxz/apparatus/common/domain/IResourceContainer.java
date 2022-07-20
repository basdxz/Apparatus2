package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.resource.IResource;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;

public interface IResourceContainer<RESOURCE_TYPE extends IResourceType, RESOURCE extends IResource<RESOURCE_TYPE>> {
    default RESOURCE_TYPE resourceType() {
        return location().resourceType();
    }

    ILocation<RESOURCE_TYPE> location();

    IResourceContainer<RESOURCE_TYPE, RESOURCE> resource(@NonNull RESOURCE resource);

    default void ensureSameLocation(@NonNull RESOURCE resource) {
        if (!location().locationEquals(resource.location()))
            throw new IllegalArgumentException("Wrong location in resource"); //TODO: Improve exceptions
    }

    void reset();

    RESOURCE resource();

    default void ensureNotEmpty() {
        if (isEmpty())
            throw new IllegalArgumentException("Resource is empty"); //TODO: Improve exceptions
    }

    boolean isEmpty();
}
