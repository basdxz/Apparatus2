package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IResource;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;
import lombok.experimental.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Accessors(fluent = true, chain = true)
@RequiredArgsConstructor(access = PROTECTED)
public class ResourceType<RESOURCE extends IResource> implements IResourceType<RESOURCE> {
    protected final Class<RESOURCE> resourceClass;
    protected final String fileExtension;
}
