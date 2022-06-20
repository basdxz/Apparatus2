package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class Domain implements IDomain {
    @NonNull
    protected final String domainName;

    @Override
    public ILocation newLocation(@NonNull String path) {
        return new Location(this, path);
    }
}
