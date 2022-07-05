package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class EntityID implements IEntityID {
    protected final IParaRegistry registry; //TODO: Re-link
    protected final String entityName;

    protected EntityID(@NonNull IParaRegistry registry, @NonNull String entityName) {
        this.registry = registry;
        this.entityName = entityName.intern();
    }

    @Override
    public String toString() {
        return entityIDToString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof IEntityID))
            return false;
        return entityIDEquals((IEntityID) obj);
    }
}
