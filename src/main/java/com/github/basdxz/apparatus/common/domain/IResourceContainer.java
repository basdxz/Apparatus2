package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.resource.IResource;
import lombok.*;

public interface IResourceContainer<RESOURCE extends IResource> {
    ILocation<RESOURCE> location();

    IResourceContainer<RESOURCE> resource(@NonNull RESOURCE resource);

    //default void ensureSameLocation(@NonNull RESOURCE resource) {
    //    if (!location().locationEquals(resource.location()))
    //        throw new IllegalArgumentException("Wrong location in resource"); //TODO: Improve exceptions
    //}

    void reset();

    RESOURCE resource();

    default void ensureNotEmpty() {
        if (isEmpty())
            throw new IllegalArgumentException("Resource is empty"); //TODO: Improve exceptions
    }

    boolean isEmpty();
}
