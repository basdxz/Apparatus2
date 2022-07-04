package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.loader.IParaLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.impl.ParaLoaderRegistry;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import lombok.*;
import lombok.experimental.*;

import java.util.*;

import static lombok.AccessLevel.NONE;

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
    protected final Map<IParaID, IParaThing> paraThings = new HashMap<>(); //TODO: Switch to alpha-numeric TreeMap
    @Getter(NONE)
    protected final List<IRecipe> recipes = new ArrayList<>();
    @Getter(NONE)
    protected final IParaLoaderRegistry loaderRegistry = new ParaLoaderRegistry(this);

    @Override
    public IParaID newParaID(@NonNull String paraName) {
        return new ParaID(this, paraName);
    }

    @Override
    public Optional<IParaThing> paraThing(@NonNull IParaID paraID) {
        return Optional.ofNullable(paraThings.get(paraID));
    }

    @Override
    public Iterable<IParaThing> paraThings() {
        return paraThings.values();
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
    public void register(@NonNull IParaThing paraThing) {
        ensureValidParaID(paraThing);
        ensureNoDuplicate(paraThing);
        addParaThing(paraThing);
    }

    protected void ensureValidParaID(@NonNull IParaThing paraThing) {
        if (paraThing.paraID() == null)
            throw new IllegalArgumentException("ParaID is null"); //TODO: proper exception
        if (!this.equals(paraThing.paraID().registry()))
            throw new IllegalArgumentException("Registry doesn't match"); //TODO: proper exception
    }

    protected void ensureNoDuplicate(@NonNull IParaThing paraThing) {
        if (paraThings.containsKey(paraThing.paraID()))
            throw new IllegalArgumentException("ParaThing already registered"); //TODO: proper exception
    }

    protected void addParaThing(@NonNull IParaThing paraThing) {
        paraThings.put(paraThing.paraID(), paraThing);
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        recipes.add(recipe);
        //TODO: Sanity checking
    }
}
