package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;
import lombok.experimental.*;

// Has domain
// Has classpath
// Registers IParaThings
// Provides locations
@Data
@Accessors(fluent = true, chain = true)
public class ParaRegistry implements IParaRegistry {
    @NonNull
    protected final String registryName;
    @NonNull
    protected final String classPath;
    @NonNull
    protected final IDomain domain;
}
