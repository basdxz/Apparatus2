package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.*;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IDomainLoader;
import com.github.basdxz.apparatus.common.loader.impl.DomainLoader;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.resourceold.IResourceOld;
import lombok.*;
import lombok.experimental.*;

import java.util.*;


@Accessors(fluent = true, chain = true)
public class Domain implements IInternalDomain {
    @Getter
    protected final String domainName;
    protected final IInternalDomainRegistry registry;

    //TODO: Some of these should be tree sets
    protected final List<String> loaderPackages = new ArrayList<>();
    protected final Map<ILocation, IResourceOld> resources = new HashMap<>();
    protected final Map<String, ILocation> locations = new HashMap<>();
    protected final Set<IRecipe> recipes = new HashSet<>();
    protected final Map<IEntityID, IEntity> entities = new HashMap<>();
    protected final Map<String, IEntityID> entityIDs = new HashMap<>();

    protected Domain(@NonNull String domainName, @NonNull IInternalDomainRegistry registry) {
        this.domainName = domainName.intern();
        this.registry = registry;
    }

    @Override
    public void addLoaderPackages(@NonNull String... packageNames) {
        loaderPackages.addAll(Arrays.asList(packageNames));
    }

    @Override
    public IDomainLoader newLoader() {
        return new DomainLoader(this);
    }

    @Override
    public Iterable<IEntity> entities() {
        return entities.values();
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
    public Iterable<IRecipe> recipes() {
        return Collections.unmodifiableSet(recipes);
    }

    @Override
    public Iterable<IResourceOld> resources() {
        return resources.values();
    }

    @Override
    public Optional<IResourceOld> resource(@NonNull String path) {
        return resource(location(path));
    }

    @Override
    public Optional<IResourceOld> resource(@NonNull ILocation location) {
        return Optional.ofNullable(resources.get(location));
    }

    @Override
    public ILocation location(@NonNull String path) {
        return locations.computeIfAbsent(path.intern(), this::newLocation);
    }

    //TODO: Fix
    protected ILocation newLocation(@NonNull String path) {
        return new Location(this, path, new IResourceType() {
            @Override
            public String extension() {
                return "null";//TODO: Technically not null :>
            }
        });
    }

    @Override
    public void register(@NonNull IEntity entity) {
        val entityID = entity.entityID();
        ensureNoDuplicate(entityID);
        add(entity, entityID);
        registerInRegistry(entity);
    }

    protected void ensureNoDuplicate(@NonNull IEntityID entityID) {
        if (entities.containsKey(entityID))
            throw new IllegalArgumentException("Entity already exists");//TODO: Better exceptions
    }

    protected void add(@NonNull IEntity entity, @NonNull IEntityID entityID) {
        entities.put(entityID, entity);
    }

    protected void registerInRegistry(@NonNull IEntity entity) {
        registry.register(entity);
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        ensureNoDuplicate(recipe);
        add(recipe);
        registerInRegistry(recipe);
    }

    protected void ensureNoDuplicate(@NonNull IRecipe recipe) {
        if (recipes.contains(recipe))
            throw new IllegalArgumentException("Recipe already exists");//TODO: Better exceptions
    }

    protected void add(@NonNull IRecipe recipe) {
        recipes.add(recipe);
    }

    protected void registerInRegistry(@NonNull IRecipe recipe) {
        registry.register(recipe);
    }

    @Override
    public void register(@NonNull IResourceOld resource) {
        val location = resource.location();
        ensureNoDuplicate(location);
        add(resource, location);
        registerInRegistry(resource);
    }

    protected void ensureNoDuplicate(@NonNull ILocation location) {
        if (resources.containsKey(location))
            throw new IllegalArgumentException("Entity already exists");//TODO: Better exceptions
    }

    protected void add(@NonNull IResourceOld resource, @NonNull ILocation location) {
        resources.put(location, resource);
    }

    protected void registerInRegistry(@NonNull IResourceOld resource) {
        registry.register(resource);
    }

    @Override
    public List<String> loaderPackages() {
        return Collections.unmodifiableList(loaderPackages);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof IDomain))
            return false;
        return domainEquals((IDomain) obj);
    }

    @Override
    public String toString() {
        return domainToString();
    }
}
