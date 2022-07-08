package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.resourceold.IResourceType;
import lombok.*;
import lombok.experimental.*;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public enum ResourceType implements IResourceType {
    MODEL_PROPERTIES("json"),
    TEXTURE("png"),
    MESH("obj");

    private final String extension;
}
