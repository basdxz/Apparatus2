package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.domain.IInternalDomain;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IInternalDomainLoader;
import com.github.basdxz.apparatus.common.loader.Loader;
import com.github.basdxz.apparatus.common.loader.context.IInitContext;
import com.github.basdxz.apparatus.common.loader.context.IPostInitContext;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.context.impl.InitContext;
import com.github.basdxz.apparatus.common.loader.context.impl.PostInitContext;
import com.github.basdxz.apparatus.common.loader.context.impl.PreInitContext;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import lombok.*;
import lombok.experimental.*;
import org.apache.commons.collections4.map.LazyMap;

import java.util.*;

@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public class DomainLoader implements IInternalDomainLoader {
    @Getter
    @NonNull
    protected final IInternalDomain domain;
    @NonNull
    protected final String[] packageNames;

    protected boolean postInitComplete = false;
    protected boolean initComplete = false;
    protected final Set<IEntityLoader<IEntity>> entityLoaders = new HashSet<>();//TODO: Switch stuff to TreeSets??
    protected final Map<IEntityLoader<IEntity>, List<IEntity>> registeredEntities = newRegisteredEntities();
    protected final Map<IEntityLoader<IEntity>, List<IRecipe>> registeredRecipes = newRegisteredRecipes();

    protected Map<IEntityLoader<IEntity>, List<IEntity>> newRegisteredEntities() {
        return LazyMap.lazyMap(new HashMap<>(), this::newEntityList);
    }

    protected List<IEntity> newEntityList() {
        return postInitComplete ? Collections.emptyList() : new ArrayList<>();
    }

    protected Map<IEntityLoader<IEntity>, List<IRecipe>> newRegisteredRecipes() {
        return LazyMap.lazyMap(new HashMap<>(), this::newRecipeList);
    }

    protected List<IRecipe> newRecipeList() {
        return initComplete ? Collections.emptyList() : new ArrayList<>();
    }

    @Override
    public void preInit() {
        populateLoaders();
        preInitEntityLoaders();
        finalizeRegisteredEntities();
        completePreInit();
    }

    //TODO: implement resource loading at this stage
    protected void populateLoaders() {
        @Cleanup val scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .acceptPackages(packageNames)
                .scan();
        for (val loaderClassInfo : scanResult.getClassesWithAnnotation(Loader.class)) {
            val loaderAnnotation = instantiateLoaderAnnotation(loaderClassInfo);
            if (!equalDomainName(loaderAnnotation))
                continue;
            ensureValidLoader(loaderClassInfo);
            entityLoaders.add(instantiateLoader(loaderClassInfo));
        }
    }

    protected Loader instantiateLoaderAnnotation(@NonNull ClassInfo loaderClassInfo) {
        val annotationInfo = loaderClassInfo.getAnnotationInfo(Loader.class);
        return (Loader) annotationInfo.loadClassAndInstantiate();
    }

    protected boolean equalDomainName(@NonNull Loader loaderAnnotation) {
        return domain.domainName().equals(loaderAnnotation.domainName());
    }

    //TODO: Check that the class has a no args constructor
    //TODO: Maybe check that stuff isn't private
    protected void ensureValidLoader(@NonNull ClassInfo loaderClassInfo) {
        if (!loaderClassInfo.implementsInterface(IEntityLoader.class))
            throw new IllegalArgumentException("Should implement IEntityLoader"); //TODO: Proper Exceptions
        if (loaderClassInfo.isAbstract())
            throw new IllegalArgumentException("Annotated loader cannot be abstract"); //TODO: Proper Exceptions
    }

    @SuppressWarnings("unchecked")
    protected IEntityLoader<IEntity> instantiateLoader(@NonNull ClassInfo loaderClassInfo) {
        try {
            return (IEntityLoader<IEntity>) loaderClassInfo.loadClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Failed to create new loader, perhaps missing a no args constructor?", e); //TODO: Proper Exceptions
        }
    }

    protected void preInitEntityLoaders() {
        entityLoaders.forEach(this::preInitEntityLoader);
    }

    protected void preInitEntityLoader(@NonNull IEntityLoader<IEntity> entityLoader) {
        entityLoader.preInit(newPreInitContext(entityLoader));
    }

    protected IPreInitContext<IEntity> newPreInitContext(@NonNull IEntityLoader<IEntity> entityLoader) {
        return new PreInitContext(this, entityLoader);
    }

    protected void finalizeRegisteredEntities() {
        registeredEntities.replaceAll((entityLoader, entityList) -> Collections.unmodifiableList(entityList));
    }

    protected void completePreInit() {
        postInitComplete = true;
    }

    @Override
    public void init() {
        initEntityLoaders();
        finalizeRegisteredRecipes();
        completeInit();
    }

    protected void initEntityLoaders() {
        entityLoaders.forEach(this::initEntityLoader);
    }

    protected void initEntityLoader(@NonNull IEntityLoader<IEntity> entityLoader) {
        entityLoader.init(newInitContext(entityLoader));
    }

    protected IInitContext<IEntity> newInitContext(@NonNull IEntityLoader<IEntity> entityLoader) {
        return new InitContext(this, entityLoader);
    }

    protected void finalizeRegisteredRecipes() {
        registeredRecipes.replaceAll((entityLoader, recipeList) -> Collections.unmodifiableList(recipeList));
    }

    protected void completeInit() {
        initComplete = true;
    }

    @Override
    public void postInit() {
        postInitEntityLoaders();
        cleanup();
    }

    protected void postInitEntityLoaders() {
        entityLoaders.forEach(this::postInitEntityLoader);
    }

    protected void postInitEntityLoader(@NonNull IEntityLoader<IEntity> entityLoader) {
        entityLoader.postInit(newPostInitContext(entityLoader));
    }

    protected IPostInitContext<IEntity> newPostInitContext(@NonNull IEntityLoader<IEntity> entityLoader) {
        return new PostInitContext(this, entityLoader);
    }

    protected void cleanup() {
        entityLoaders.clear();
        registeredEntities.clear();
    }

    @Override
    public void register(@NonNull IPreInitContext<IEntity> preInitContext, @NonNull IEntity entity) {
        ensurePreInitNotComplete();
        val entityList = entityList(preInitContext);
        ensureNoDuplicate(entityList, entity);
        add(entityList, entity);
        registerInDomain(entity);
    }

    protected void ensurePreInitNotComplete() {
        if (postInitComplete)
            throw new IllegalStateException("PreInit already Complete!");//TODO: Better exceptions
    }

    protected List<IEntity> entityList(@NonNull IPreInitContext<IEntity> preInitContext) {
        return registeredEntities.get(preInitContext.entityLoader());
    }

    protected void ensureNoDuplicate(@NonNull List<IEntity> entityList, @NonNull IEntity entity) {
        if (entityList.contains(entity))
            throw new IllegalArgumentException("Entity already exists");//TODO: Better exceptions
    }

    protected void add(@NonNull List<IEntity> entityList, @NonNull IEntity entity) {
        entityList.add(entity);
    }

    protected void registerInDomain(@NonNull IEntity entity) {
        domain.register(entity);
    }

    @Override
    public void register(@NonNull IInitContext<IEntity> initContext, @NonNull IRecipe recipe) {
        ensureInitNotComplete();
        val recipeList = recipeList(initContext);
        ensureNoDuplicate(recipeList, recipe);
        add(recipeList, recipe);
        registerInDomain(recipe);
    }

    protected void ensureInitNotComplete() {
        if (initComplete)
            throw new IllegalStateException("Init already Complete!");//TODO: Better exceptions
    }

    protected List<IRecipe> recipeList(@NonNull IInitContext<IEntity> initContext) {
        return registeredRecipes.get(initContext.entityLoader());
    }

    protected void ensureNoDuplicate(@NonNull List<IRecipe> recipeList, @NonNull IRecipe recipe) {
        if (recipeList.contains(recipe))
            throw new IllegalArgumentException("Recipe already exists");//TODO: Better exceptions
    }

    protected void add(@NonNull List<IRecipe> recipeList, @NonNull IRecipe recipe) {
        recipeList.add(recipe);
    }

    protected void registerInDomain(@NonNull IRecipe recipe) {
        domain.register(recipe);
    }

    @Override
    public List<IEntity> registeredEntities(@NonNull IEntityLoader<IEntity> entityLoader) {
        return registeredEntities.get(entityLoader);
    }
}
