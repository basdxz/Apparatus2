package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.registry.IEntityID;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class EntityID implements IEntityID {
    @NonNull
    protected final IParaRegistry registry;
    @NonNull
    protected final String paraName;

    @Override
    public String toString() {
        return toStringParaID();
    }
}
