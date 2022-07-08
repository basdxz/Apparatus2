package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IResourceType;
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
