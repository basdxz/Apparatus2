package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IDomainLoader;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.resource.IResource;
import com.github.basdxz.apparatus.common.resource.IResourceType;
import lombok.*;

import java.util.Collection;
import java.util.Optional;

public interface IDomain {
    //TODO: Add Hash too
    default boolean domainEquals(@NonNull IDomain domain) {
        return domainName().equals(domain.domainName());
    }

    default String domainToString() {
        return domainName();
    }

    String domainName();

    void addLoaderPackages(@NonNull String... packageNames);

    IDomainLoader newLoader();

    Collection<IEntity> entities();

    Optional<IEntity> entity(@NonNull String entityName);

    Optional<IEntity> entity(@NonNull IEntityID entityID);

    IEntityID entityID(@NonNull String entityName);

    Collection<IRecipe> recipes();

    <RESOURCE extends IResource> IResourceContainer<RESOURCE> resourceContainer(@NonNull String path,
                                                                                @NonNull IResourceType<RESOURCE> resourceType);
}
