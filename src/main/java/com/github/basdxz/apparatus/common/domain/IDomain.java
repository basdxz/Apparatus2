package com.github.basdxz.apparatus.common.domain;

import lombok.*;

// Domain name

// ILocation
// IResource

// IEntityID
// IEntity

// IParaID
// IParaThing
public interface IDomain {
    default String toStringDomain() {
        return domainName();
    }

    String domainName();

    ILocation location(@NonNull String path);
}
