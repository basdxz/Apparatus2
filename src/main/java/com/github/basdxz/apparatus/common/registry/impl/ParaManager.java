package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.loader.IParaLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.impl.ParaLoaderRegistry;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import lombok.*;
import lombok.experimental.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    protected final Map<IParaID, IParaThing> paraThingsMap = new HashMap<>();//TODO: Switch to alpha-numeric TreeMap

    @Getter(NONE)
    protected final IParaLoaderRegistry loaderRegistry = new ParaLoaderRegistry(this);

    @Override
    public Optional<IParaThing> paraThing(@NonNull IParaID paraID) {
        return Optional.ofNullable(paraThingsMap.get(paraID));
    }

    @Override
    public Iterable<IParaThing> paraThings() {
        return paraThingsMap.values();
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
}
