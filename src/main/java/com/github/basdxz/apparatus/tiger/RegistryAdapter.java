package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.loader.IIInitializeable;
import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaIInitializeableRegistry;
import lombok.*;

public class RegistryAdapter implements IIInitializeable {
    protected final IParaIInitializeableRegistry registry;

    public RegistryAdapter(@NonNull IParaIInitializeableRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void preInit() {
        registry.preInit();
        registry.paraThings().forEach(this::adapt);
    }

    public void adapt(@NonNull IParaThing paraThing) {
        if (paraThing instanceof IParaItem)
            new ParaItemAdapter((IParaItem) paraThing);
    }

    @Override
    public void init() {
        registry.init();
    }

    @Override
    public void postInit() {
        registry.postInit();
    }
}
