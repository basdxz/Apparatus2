package com.github.basdxz.apparatus.common.resource;

import com.github.basdxz.apparatus.common.domain.ILocation;

//TODO: Hash/Equals/toString
public interface IResource<RESOURCE_TYPE extends IResourceType> {
    default RESOURCE_TYPE resourceType() {
        return location().resourceType();
    }

    ILocation<RESOURCE_TYPE> location();
}
