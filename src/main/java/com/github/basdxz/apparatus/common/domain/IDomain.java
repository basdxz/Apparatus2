package com.github.basdxz.apparatus.common.domain;

import lombok.*;

public interface IDomain {
    String domainName();

    ILocation newLocation(@NonNull String path);
}
