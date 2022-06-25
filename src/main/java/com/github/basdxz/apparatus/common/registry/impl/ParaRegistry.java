package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.loader.IIInitializeable;
import com.github.basdxz.apparatus.common.loader.ILoader;
import com.github.basdxz.apparatus.common.loader.ILoadingContext;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;
import lombok.experimental.*;

import java.util.*;

import static lombok.AccessLevel.NONE;


@Data
@Accessors(fluent = true, chain = true)
public class ParaRegistry implements IParaRegistry, IIInitializeable {
    @NonNull
    protected final String registryName;
    @NonNull
    protected final String classPath;
    @NonNull
    protected final IDomain domain;
    @Getter(NONE)
    protected final Map<IParaID, IParaThing> paraThings = new HashMap<>();//TODO: Switch to alpha-numeric TreeMap
    @Getter(NONE)
    protected final Set<ILoader<IParaThing>> loaders = new HashSet<>();//TODO: Switch to alpha-numeric TreeSet

    @Override
    public Optional<IParaThing> paraThing(@NonNull IParaID paraID) {
        return Optional.ofNullable(paraThings.get(paraID));
    }

    @Override
    public Iterable<IParaThing> paraThings() {
        return paraThings.values();
    }

    @Override
    public void preInit() {
        // Find all loaders
        val context = new PreInitContext();
        loaders.forEach((l) -> l.preInit(context));
    }

    @Override
    public void init() {
        val context = new InitContext();
        loaders.forEach((l) -> l.init(context));
    }

    @Override
    public void postInit() {
        val context = new PostInitContext();
        loaders.forEach((l) -> l.postInit(context));
        loaders.clear();
    }

    protected class PreInitContext extends LoadingContext implements ILoadingContext.IPreInit<IParaThing> {
        @Override
        public void register(@NonNull IParaThing paraThing) {
            validateParaThing(paraThing);
            validateNoDuplicate(paraThing);
            addParaThing(paraThing);
        }

        protected void validateParaThing(@NonNull IParaThing paraThing) {
            if (paraThing.paraID() == null)
                throw new IllegalArgumentException("ParaID is null");//TODO: proper exception
            if (!registry().equals(paraThing.paraID().registry()))
                throw new IllegalArgumentException("Registry doesn't match");//TODO: proper exception
        }

        protected void validateNoDuplicate(@NonNull IParaThing paraThing) {
            if (paraThings.get(paraThing.paraID()) != null)
                throw new IllegalArgumentException("ParaThing already registered");//TODO: proper exception
        }

        protected void addParaThing(@NonNull IParaThing paraThing) {
            paraThings.put(paraThing.paraID(), paraThing);
        }

        @Override
        public IParaID newParaID(@NonNull String paraName) {
            return new ParaID(registry(), paraName);
        }
    }

    protected class InitContext extends LoadingContext implements ILoadingContext.IInit<IParaThing> {
    }

    protected class PostInitContext extends LoadingContext implements ILoadingContext.IPostInit<IParaThing> {
    }

    protected abstract class LoadingContext implements ILoadingContext<IParaThing> {
        @Override
        public IParaRegistry registry() {
            return ParaRegistry.this;
        }
    }
}
