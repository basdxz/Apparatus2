package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.*;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IDomainLoader;
import com.github.basdxz.apparatus.common.loader.impl.DomainLoader;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.resource.IResource;
import com.github.basdxz.apparatus.common.resource.IResourceType;
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
    protected final Map<ILocation<?>, IResourceContainer<?, ?>> resourceContainers = new HashMap<>();
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
    public IDomainLoader newLoader() {//TODO: only give 1
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
    public void resetResources() {
        resourceContainers.values().forEach(IResourceContainer::reset);
    }

    @Override
    public void registerResources(@NonNull IResourceProvider provider) {
        resourceContainers.values().stream()
                          .filter(IResourceContainer::isEmpty)
                          .forEach(provider::tryProviding);
    }

    @Override
    public void ensureAllResourcesRegistered() {
        resourceContainers.values().forEach(IResourceContainer::ensureNotEmpty);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RESOURCE_TYPE extends IResourceType, RESOURCE extends IResource<RESOURCE_TYPE>>
    IResourceContainer<RESOURCE_TYPE, RESOURCE> resourceContainer(@NonNull String path,
                                                                  @NonNull RESOURCE_TYPE resourceType) {
        return (IResourceContainer<RESOURCE_TYPE, RESOURCE>) resourceContainers.computeIfAbsent(
                newLocation(path, resourceType), this::newResourceContainer);
    }

    protected <RESOURCE_TYPE extends IResourceType> ILocation<RESOURCE_TYPE>
    newLocation(@NonNull String path, @NonNull RESOURCE_TYPE resourceType) {
        return new Location<>(this, path, resourceType);
    }

    public <RESOURCE_TYPE extends IResourceType, RESOURCE extends IResource<RESOURCE_TYPE>>
    IResourceContainer<RESOURCE_TYPE, RESOURCE> newResourceContainer(@NonNull ILocation<RESOURCE_TYPE> location) {
        return new ResourceContainer<>(location);
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
