package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.IEntityID;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class EntityID implements IEntityID {
    protected final IDomain domain;
    protected final String entityName;

    protected EntityID(@NonNull IDomain domain, @NonNull String entityName) {
        this.domain = domain;
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
