package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IDomainLoader;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
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

    void addLoaderPackages(@NonNull String... packageNames);

    IDomainLoader newLoader();

    //TODO: Should this be iterable?
    Iterable<IEntity> entities();

    Optional<IEntity> entity(@NonNull String entityName);

    Optional<IEntity> entity(@NonNull IEntityID entityID);

    IEntityID entityID(@NonNull String entityName);

    //TODO: Should this be iterable?
    Iterable<IRecipe> recipes();

    //TODO: Should this be iterable?
    Iterable<IResource> resources();

    Optional<IResource> resource(@NonNull String path);

    Optional<IResource> resource(@NonNull ILocation location);

    ILocation location(@NonNull String path);
}
