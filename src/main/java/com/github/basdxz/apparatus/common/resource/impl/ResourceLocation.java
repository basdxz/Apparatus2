package com.github.basdxz.apparatus.common.resource.impl;

import com.github.basdxz.apparatus.common.resource.IResourceLocation;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class ResourceLocation implements IResourceLocation {
    @NonNull
    protected final String domain;
    @NonNull
    protected final String path;
}
