package com.github.basdxz.apparatus.common.resourceold.impl;

import com.github.basdxz.apparatus.common.resourceold.IResourceTypeOld;
import lombok.*;
import lombok.experimental.*;

@Getter
@Deprecated
@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public enum ResourceTypeOld implements IResourceTypeOld {
    MODEL_PROPERTIES("json"),
    TEXTURE("png"),
    MESH("obj");

    private final String extension;
}
