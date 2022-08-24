package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.resource.IResource;
import com.github.basdxz.apparatus.common.resource.IResourceType;

//TODO: hash/equals/tostring
public interface ILocation<RESOURCE extends IResource> {
    IResourceType<RESOURCE> resourceType();

    IDomain domain();

    String path();
}
