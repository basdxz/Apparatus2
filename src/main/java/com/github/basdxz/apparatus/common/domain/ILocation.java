package com.github.basdxz.apparatus.common.domain;

import lombok.*;

public interface ILocation {
    default boolean locationEquals(@NonNull ILocation location) {
        return domain().domainEquals(location.domain()) && path().equals(location.path());
    }

    default String locationToString() {
        return domain().domainToString() + ":" + path();
    }

    IDomain domain();

    String path();
}
