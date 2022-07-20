package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;

//TODO: Add hash
public interface ILocation<RESOURCE_TYPE extends IResourceType> {
    default boolean locationEquals(@NonNull ILocation<?> location) {
        return resourceType().equals(location.resourceType()) &&
               domain().domainEquals(location.domain()) &&
               path().equals(location.path());
    }

    default String locationToString() {
        return domain().domainToString() + ":" + path() + "." + resourceType().resourceTypeToString();
    }

    IDomain domain();

    RESOURCE_TYPE resourceType();

    String path();
}
