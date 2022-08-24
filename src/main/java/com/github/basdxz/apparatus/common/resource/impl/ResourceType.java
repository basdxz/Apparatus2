package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;
import lombok.experimental.*;

@Getter
@Deprecated
@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public enum ResourceType implements IResourceType {
    MODEL_PROPERTIES("json"),
    TEXTURE("png"),
    MESH("obj");

    private final String fileExtension;

    @Override
    public Class resourceClass() {
        return null;
    }

    @Override
    public String fileExtension() {
        return null;
    }
}
