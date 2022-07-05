package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class EntityID implements IEntityID {
    @NonNull
    protected final IParaRegistry registry;
    @NonNull
    protected final String entityName;

    @Override
    public String toString() {
        return toStringParaID();
    }
}
