package com.github.basdxz.apparatus.common.resource;

import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.domain.IResourceType;

//TODO: Hash/Equals/toString
public interface IResource {
    default IResourceType resourceType() {
        return location().resourceType();
    }

    ILocation location();
}
