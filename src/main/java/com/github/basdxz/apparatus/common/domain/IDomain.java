package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.resource.IResource;
import lombok.*;

import java.util.Optional;

public interface IDomain {
    default boolean domainEquals(@NonNull IDomain domain) {
        return domainName().equals(domain.domainName());
    }

    default String domainToString() {
        return domainName();
    }

    String domainName();

    ILocation location(@NonNull String path);

    void resource(@NonNull IResource resource);

    Optional<IResource> resource(@NonNull ILocation location);

    IEntityID entityID(@NonNull String entityName);

    void entity(@NonNull IEntity entity);

    Optional<IEntity> entity(@NonNull IEntityID entityID);
}
