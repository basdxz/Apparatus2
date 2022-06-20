package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class ParaID implements IParaID {
    @NonNull
    protected final IParaRegistry registry;
    @NonNull
    protected final String paraName;
}
