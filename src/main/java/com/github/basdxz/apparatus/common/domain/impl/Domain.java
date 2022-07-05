package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import lombok.*;
import lombok.experimental.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(fluent = true, chain = true)
public class Domain implements IDomain {
    protected static final Map<String, IDomain> domains = new HashMap<>();

    protected final String domainName;

    protected Domain(@NonNull String domainName) {
        this.domainName = domainName.intern();
    }

    public static IDomain get(@NonNull String domainName) {
        return domains.computeIfAbsent(domainName.intern(), Domain::new);
    }

    @Override
    public ILocation location(@NonNull String path) {
        return new Location(this, path);
    }

    @Override
    public String toString() {
        return toStringDomain();
    }
}
