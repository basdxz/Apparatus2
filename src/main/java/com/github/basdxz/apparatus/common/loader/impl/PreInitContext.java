package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.loader.IParaLoader;
import com.github.basdxz.apparatus.common.loader.IParaLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.IPreInitContext;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaID;
import lombok.*;

public class PreInitContext extends LoadingContext implements IPreInitContext<IParaThing> {
    public PreInitContext(@NonNull IParaLoaderRegistry loaderRegistry, @NonNull IParaLoader<IParaThing> loader) {
        super(loaderRegistry, loader);
    }

    @Override
    public void register(@NonNull IParaThing paraThing) {
        loaderRegistry.register(this, paraThing);
    }

    @Override
    public IParaID newParaID(@NonNull String paraName) {
        return registry().newParaID(paraName);
    }
}
