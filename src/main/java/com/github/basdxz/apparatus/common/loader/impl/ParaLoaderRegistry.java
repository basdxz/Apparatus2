package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.loader.IParaLoader;
import com.github.basdxz.apparatus.common.loader.IParaLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.RegisteredLoader;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import lombok.*;

import java.util.*;

@RequiredArgsConstructor
public class ParaLoaderRegistry implements IParaLoaderRegistry {
    @NonNull
    protected final IParaManager manager;

    protected final Set<IParaLoader<IParaThing>> loaders = new HashSet<>(); //TODO: Switch to alpha-numeric TreeSet
    protected final Map<IParaLoader<IParaThing>, List<IParaThing>> loadedParaThings = new HashMap<>();

    @Override
    public void preInit() {
        populateLoaders();
        loaders.forEach(loader -> loader.preInit(new PreInitContext(this, loader)));
    }

    protected void populateLoaders() {
        @Cleanup val scanResult = new ClassGraph()
                .enableAnnotationInfo()
                .acceptPackages(manager.loadersPackage())
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
        return manager.registryName().equals(loaderAnnotation.registryName());
    }

    //TODO: Check that the class has a no args constructor
    //TODO: Maybe check that stuff isn't private
    protected void ensureValidLoader(@NonNull ClassInfo loaderClassInfo) {
        if (!loaderClassInfo.implementsInterface(IParaLoader.class))
            throw new IllegalArgumentException("Should implement ILoader");//TODO: Proper Exceptions, but honestly just event-based bindings
        if (loaderClassInfo.isAbstract())
            throw new IllegalArgumentException("Annotated loader cannot be abstract");//TODO: Proper Exceptions
    }

    @SuppressWarnings("unchecked")
    protected IParaLoader<IParaThing> instantiateLoader(@NonNull ClassInfo loaderClassInfo) {
        try {
            return (IParaLoader<IParaThing>) loaderClassInfo.loadClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Failed to create new loader, perhaps missing a no args constructor?", e);//TODO: Proper Exceptions
        }
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
    public void register(@NonNull IPreInitContext<IParaThing> context, @NonNull IParaThing paraThing) {
        registerInManager(paraThing);
        addParaThing(context, paraThing);
    }

    protected void registerInManager(@NonNull IParaThing paraThing) {
        manager.register(paraThing);
    }

    protected void addParaThing(@NonNull IPreInitContext<IParaThing> context, @NonNull IParaThing paraThing) {
        loadedParaThings(context.loader()).add(paraThing);
    }

    @Override
    public List<IParaThing> loadedParaThings(@NonNull IParaLoader<IParaThing> loader) {
        return loadedParaThings.computeIfAbsent(loader, key -> new ArrayList<>());
    }
}
