package com.github.basdxz.apparatus.common.domain;

import lombok.*;

//TODO: Add hash
public interface ILocation {
    default boolean locationEquals(@NonNull ILocation location) {
        return domain().domainEquals(location.domain()) &&
                path().equals(location.path()) &&
                resourceType().equals(location.resourceType());
    }

    default String locationToString() {
        return domain().domainToString() + ":" + path() + "." + resourceType().resourceToString();
    }

    IDomain domain();

    String path();

    IResourceType resourceType();
}
