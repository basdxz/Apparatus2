package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class Location implements ILocation {
    protected final IDomain domain;
    protected final String path;

    protected Location(@NonNull IDomain domain, @NonNull String path) {
        this.domain = domain;
        this.path = path.intern();
    }

    @Override
    public String toString() {
        return locationToString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ILocation))
            return false;
        return locationEquals((ILocation) obj);
    }
}
