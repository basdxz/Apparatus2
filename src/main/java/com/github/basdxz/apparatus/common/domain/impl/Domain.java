package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.domain.IInternalDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IDomainLoader;
import com.github.basdxz.apparatus.common.loader.impl.DomainLoader;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.resource.IResource;
import lombok.*;
import lombok.experimental.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// TODO: Allow lookups in the form of minecraft:stick or apparatus:red_square
@Data
@Accessors(fluent = true, chain = true)
public class Domain implements IInternalDomain {
    protected static final Map<String, IDomain> domains = new HashMap<>();

    protected final String domainName;

    protected final Map<ILocation, IResource> resources = new HashMap<>();
    protected final Map<String, ILocation> locations = new HashMap<>();
    protected final Map<IEntityID, IEntity> entities = new HashMap<>();
    protected final Map<String, IEntityID> entityIDs = new HashMap<>();

    protected Domain(@NonNull String domainName) {
        this.domainName = domainName.intern();
    }

    public static IDomain get(@NonNull String domainName) {
        return domains.computeIfAbsent(domainName.intern(), Domain::new);
    }

    @Override
    public IDomainLoader newLoader(@NonNull String... packageNames) {
        return new DomainLoader(this, packageNames);
    }

    @Override
    public Optional<IEntity> entity(@NonNull String entityName) {
        return entity(entityID(entityName));
    }

    @Override
    public Optional<IEntity> entity(@NonNull IEntityID entityID) {
        return Optional.ofNullable(entities.get(entityID));
    }

    @Override
    public IEntityID entityID(@NonNull String entityName) {
        return entityIDs.computeIfAbsent(entityName.intern(), this::newEntityID);
    }

    protected IEntityID newEntityID(@NonNull String path) {
        return new EntityID(this, path);
    }

    @Override
    public Optional<IResource> resource(@NonNull String path) {
        return resource(location(path));
    }

    @Override
    public Optional<IResource> resource(@NonNull ILocation location) {
        return Optional.ofNullable(resources.get(location));
    }

    @Override
    public ILocation location(@NonNull String path) {
        return locations.computeIfAbsent(path.intern(), this::newLocation);
    }

    protected ILocation newLocation(@NonNull String path) {
        return new Location(this, path);
    }

    @Override
    public void register(@NonNull IEntity entity) {
        val entityID = entity.entityID();
        ensureNoDuplicate(entityID);
        add(entity, entityID);
    }

    protected void add(@NonNull IEntity entity, @NonNull IEntityID entityID) {
        entities.put(entityID, entity);
    }

    protected void ensureNoDuplicate(@NonNull IEntityID entityID) {
        if (entities.containsKey(entityID))
            throw new IllegalArgumentException("Entity already exists");//TODO: Better exceptions
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        //TODO: Implement Recipes
    }

    @Override
    public void register(@NonNull IResource resource) {
        val location = resource.location();
        ensureNoDuplicate(location);
        add(resource, location);
    }

    protected void ensureNoDuplicate(@NonNull ILocation location) {
        if (resources.containsKey(location))
            throw new IllegalArgumentException("Entity already exists");//TODO: Better exceptions
    }

    protected void add(@NonNull IResource resource, @NonNull ILocation location) {
        resources.put(location, resource);
    }

    @Override
    public String toString() {
        return domainToString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof IDomain))
            return false;
        return domainEquals((IDomain) obj);
    }
}
