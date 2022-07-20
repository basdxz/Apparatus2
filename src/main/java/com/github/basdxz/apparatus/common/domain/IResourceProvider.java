package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.resource.IResource;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;

public interface IResourceProvider {
    <RESOURCE_TYPE extends IResourceType, RESOURCE extends IResource<RESOURCE_TYPE>>
    void tryProviding(@NonNull IResourceContainer<RESOURCE_TYPE, RESOURCE> container);
}
