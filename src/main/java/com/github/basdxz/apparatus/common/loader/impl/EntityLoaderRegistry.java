package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.domain.IEntity;
import com.github.basdxz.apparatus.common.loader.EntityLoader;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IEntityLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.IPreInitContext;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import lombok.*;

import java.util.*;

@RequiredArgsConstructor
public class EntityLoaderRegistry implements IEntityLoaderRegistry {
    @NonNull
    protected final IParaManager manager;

    protected final Set<IEntityLoader<IEntity>> loaders = new HashSet<>(); //TODO: Switch to alpha-numeric TreeSet
    protected final Map<IEntityLoader<IEntity>, List<IEntity>> loadedParaThings = new HashMap<>();

    @Override
    public void preInit() {
        populateLoaders();
        loaders.forEach(loader -> loader.preInit(new PreInitContext(this, loader)));
        finalizeLoadedParaThings();
    }

    protected void populateLoaders() {
        @Cleanup val scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .acceptPackages(manager.loadersPackage())
                .scan();

        for (val loaderClassInfo : scanResult.getClassesWithAnnotation(EntityLoader.class)) {
            val loaderAnnotation = instantiateLoaderAnnotation(loaderClassInfo);
            if (!sameRegistryName(loaderAnnotation))
                continue;

            ensureValidLoader(loaderClassInfo);
            loaders.add(instantiateLoader(loaderClassInfo));
        }
    }

    protected EntityLoader instantiateLoaderAnnotation(@NonNull ClassInfo loaderClassInfo) {
        val annotationInfo = loaderClassInfo.getAnnotationInfo(EntityLoader.class);
        return (EntityLoader) annotationInfo.loadClassAndInstantiate();
    }

    protected boolean sameRegistryName(@NonNull EntityLoader loaderAnnotation) {
        return manager.registryName().equals(loaderAnnotation.domainName());
    }

    //TODO: Check that the class has a no args constructor
    //TODO: Maybe check that stuff isn't private
    protected void ensureValidLoader(@NonNull ClassInfo loaderClassInfo) {
        if (!loaderClassInfo.implementsInterface(IEntityLoader.class))
            throw new IllegalArgumentException("Should implement ILoader");//TODO: Proper Exceptions, but honestly just event-based bindings
        if (loaderClassInfo.isAbstract())
            throw new IllegalArgumentException("Annotated loader cannot be abstract");//TODO: Proper Exceptions
    }

    @SuppressWarnings("unchecked")
    protected IEntityLoader<IEntity> instantiateLoader(@NonNull ClassInfo loaderClassInfo) {
        try {
            return (IEntityLoader<IEntity>) loaderClassInfo.loadClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Failed to create new loader, perhaps missing a no args constructor?", e);//TODO: Proper Exceptions
        }
    }

    protected void finalizeLoadedParaThings() {
        for (val loader : loaders)
            loadedParaThings.computeIfAbsent(loader, key -> Collections.emptyList());
        loadedParaThings.replaceAll((iParaThingIParaLoader, iParaThings) -> Collections.unmodifiableList(iParaThings));
    }

    @Override
    public void init() {
        loaders.forEach((loader) -> loader.init(new InitContext(this, loader)));
    }

    @Override
    public void postInit() {
        loaders.forEach((loader) -> loader.postInit(new PostInitContext(this, loader)));
        cleanup();
    }

    protected void cleanup() {
        loaders.clear();
        loadedParaThings.clear();
    }

    @Override
    public IParaRegistry registry() {
        return manager;
    }

    @Override
    public void register(@NonNull IPreInitContext<IEntity> context, @NonNull IEntity paraThing) {
        registerInManager(paraThing);
        addParaThing(context, paraThing);
    }

    protected void registerInManager(@NonNull IEntity paraThing) {
        manager.register(paraThing);
    }

    protected void addParaThing(@NonNull IPreInitContext<IEntity> context, @NonNull IEntity paraThing) {
        loadedParaThings(context.loader()).add(paraThing);
    }

    @Override
    public List<IEntity> loadedParaThings(@NonNull IEntityLoader<IEntity> loader) {
        return loadedParaThings.computeIfAbsent(loader, key -> new ArrayList<>());
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        registerInManager(recipe);
        //TODO: Pass recipes to post-init
    }

    protected void registerInManager(@NonNull IRecipe recipe) {
        manager.register(recipe);
    }
}
