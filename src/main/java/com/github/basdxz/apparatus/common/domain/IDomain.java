package com.github.basdxz.apparatus.common.domain;

import lombok.*;

public interface IDomain {
    default String toStringDomain() {
        return domainName();
    }

    String domainName();

    ILocation newLocation(@NonNull String path);
}
