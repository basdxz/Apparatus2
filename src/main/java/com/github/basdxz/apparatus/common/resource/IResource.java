package com.github.basdxz.apparatus.common.resource;

import com.github.basdxz.apparatus.common.domain.ILocation;

public interface IResource {
    ILocation location();

    IResourceType type();
}