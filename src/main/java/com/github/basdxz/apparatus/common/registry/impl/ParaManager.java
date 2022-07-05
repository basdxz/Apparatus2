package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.impl.EntityLoaderRegistry;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import lombok.*;
import lombok.experimental.*;

import java.util.*;

import static lombok.AccessLevel.NONE;

// Manager Should not exist
// Replace manager with entity/resource registry
// Registry should be disposed of once loading is complete.
@Data
@Accessors(fluent = true, chain = true)
public class ParaManager implements IParaManager {
    @NonNull
    protected final IDomain domain;
    @NonNull
    protected final String registryName;
    @NonNull
    protected final String loadersPackage;

    //TODO: Make proper finalizers
    @Getter(NONE)
    protected final Map<IEntityID, IEntity> paraThings = new HashMap<>(); //TODO: Switch to alpha-numeric TreeMap
    @Getter(NONE)
    protected final List<IRecipe> recipes = new ArrayList<>();
    @Getter(NONE)
    protected final IEntityLoaderRegistry loaderRegistry = new EntityLoaderRegistry(this);

    @Override
    public IEntityID newParaID(@NonNull String paraName) {
        return domain.entityID(paraName);
    }

    @Override
    public Optional<IEntity> paraThing(@NonNull IEntityID paraID) {
        return domain.entity(paraID);
    }

    @Override
    public Iterable<IEntity> paraThings() {
        return Collections.emptyList();//TODO: FIX NOW
    }

    @Override
    public Iterable<IRecipe> recipes() {
        return Collections.unmodifiableList(recipes);
    }

    @Override
    public void preInit() {
        loaderRegistry.preInit();
    }

    @Override
    public void init() {
        loaderRegistry.init();
    }

    @Override
    public void postInit() {
        loaderRegistry.postInit();
    }

    @Override
    public void register(@NonNull IEntity paraThing) {
        ensureValidParaID(paraThing);
        ensureNoDuplicate(paraThing);
        addParaThing(paraThing);
    }

    protected void ensureValidParaID(@NonNull IEntity paraThing) {
        if (paraThing.entityID() == null)
            throw new IllegalArgumentException("ParaID is null"); //TODO: proper exception
        if (!this.equals(paraThing.entityID().domain()))
            throw new IllegalArgumentException("Registry doesn't match"); //TODO: proper exception
    }

    protected void ensureNoDuplicate(@NonNull IEntity paraThing) {
        if (paraThings.containsKey(paraThing.entityID()))
            throw new IllegalArgumentException("ParaThing already registered"); //TODO: proper exception
    }

    protected void addParaThing(@NonNull IEntity paraThing) {
        paraThings.put(paraThing.entityID(), paraThing);
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        recipes.add(recipe);
        //TODO: Sanity checking
    }

    @Override
    public String toString() {
        return toStringParaRegistry();
    }
}
