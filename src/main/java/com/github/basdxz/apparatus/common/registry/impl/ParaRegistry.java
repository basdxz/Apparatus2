package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.loader.ILoader;
import com.github.basdxz.apparatus.common.loader.ILoadingContext;
import com.github.basdxz.apparatus.common.loader.RegisteredLoader;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.registry.IParaInitializeableRegistry;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import lombok.*;
import lombok.experimental.*;

import java.util.*;

import static lombok.AccessLevel.NONE;


@Data
@Accessors(fluent = true, chain = true)
public class ParaRegistry implements IParaInitializeableRegistry {
    @NonNull
    protected final IDomain domain;
    @NonNull
    protected final String registryName;
    @NonNull
    protected final String loadersPackage;
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
        populateLoaders();
        val context = new PreInitContext();
        loaders.forEach((loader) -> loader.preInit(context));
    }

    protected void populateLoaders() {
        @Cleanup val scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .acceptPackages(loadersPackage)
                .scan();

        for (val loaderClassInfo : scanResult.getClassesWithAnnotation(RegisteredLoader.class)) {
            val loaderAnnotation = instantiateLoaderAnnotation(loaderClassInfo);
            if (!sameRegistryName(loaderAnnotation))
                continue;

            ensureValidLoader(loaderClassInfo);
            loaders.add(instantiateLoader(loaderClassInfo));
        }
    }

    protected RegisteredLoader instantiateLoaderAnnotation(@NonNull ClassInfo loaderClassInfo) {
        val annotationInfo = loaderClassInfo.getAnnotationInfo(RegisteredLoader.class);
        return (RegisteredLoader) annotationInfo.loadClassAndInstantiate();
    }

    protected boolean sameRegistryName(@NonNull RegisteredLoader loaderAnnotation) {
        return registryName.equals(loaderAnnotation.registryName());
    }

    //TODO: Check that the class has a no args constructor
    //TODO: Maybe check that stuff isn't private
    protected void ensureValidLoader(@NonNull ClassInfo loaderClassInfo) {
        if (!loaderClassInfo.implementsInterface(ILoader.class))
            throw new IllegalArgumentException("Should implement ILoader");//TODO: Proper Exceptions, but honestly just event-based bindings
        if (loaderClassInfo.isAbstract())
            throw new IllegalArgumentException("Annotated loader cannot be abstract");//TODO: Proper Exceptions
    }

    @SuppressWarnings("unchecked")
    protected ILoader<IParaThing> instantiateLoader(@NonNull ClassInfo loaderClassInfo) {
        try {
            return (ILoader<IParaThing>) loaderClassInfo.loadClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Failed to create new loader, perhaps missing a no args constructor?", e);//TODO: Proper Exceptions
        }
    }

    @Override
    public void init() {
        val context = new InitContext();
        loaders.forEach((loader) -> loader.init(context));
    }

    @Override
    public void postInit() {
        val context = new PostInitContext();
        loaders.forEach((loader) -> loader.postInit(context));
        loaders.clear();
    }

    protected class PreInitContext extends LoadingContext implements ILoadingContext.IPreInit<IParaThing> {
        @Override
        public void register(@NonNull IParaThing paraThing) {
            ensureValidParaID(paraThing);
            ensureNoDuplicate(paraThing);
            addParaThing(paraThing);
        }

        protected void ensureValidParaID(@NonNull IParaThing paraThing) {
            if (paraThing.paraID() == null)
                throw new IllegalArgumentException("ParaID is null");//TODO: proper exception
            if (!registry().equals(paraThing.paraID().registry()))
                throw new IllegalArgumentException("Registry doesn't match");//TODO: proper exception
        }

        protected void ensureNoDuplicate(@NonNull IParaThing paraThing) {
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
