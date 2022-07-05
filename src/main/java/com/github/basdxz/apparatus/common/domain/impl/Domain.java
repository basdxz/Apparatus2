package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.resource.IResource;
import lombok.*;
import lombok.experimental.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
@Accessors(fluent = true, chain = true)
public class Domain implements IDomain {
    protected static final Map<String, IDomain> domains = new HashMap<>();

    protected final String domainName;

    protected final Map<String, ILocation> locations = new HashMap<>();
    protected final Map<ILocation, IResource> resources = new HashMap<>();
    protected final Map<String, IEntityID> entityIDs = new HashMap<>();
    protected final Map<IEntityID, IEntity> entities = new HashMap<>();

    protected Domain(@NonNull String domainName) {
        this.domainName = domainName.intern();
    }

    public static IDomain get(@NonNull String domainName) {
        return domains.computeIfAbsent(domainName.intern(), Domain::new);
    }

    @Override
    public ILocation location(@NonNull String path) {
        return locations.computeIfAbsent(path.intern(), this::newLocation);
    }

    protected ILocation newLocation(@NonNull String path) {
        return new Location(this, path);
    }

    @Override
    public void resource(@NonNull IResource resource) {
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
    public Optional<IResource> resource(@NonNull ILocation location) {
        return Optional.ofNullable(resources.get(location));
    }

    @Override
    public IEntityID entityID(@NonNull String entityName) {
        return entityIDs.computeIfAbsent(entityName.intern(), this::newEntityID);
    }

    protected IEntityID newEntityID(@NonNull String path) {
        return new EntityID(this, path);
    }

    @Override
    public void entity(@NonNull IEntity entity) {
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
    public Optional<IEntity> entity(@NonNull IEntityID entityID) {
        return Optional.ofNullable(entities.get(entityID));
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
