package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.loader.ILoadingContext;
import com.github.basdxz.apparatus.common.loader.IParaLoader;
import com.github.basdxz.apparatus.common.loader.IParaLoaderRegistry;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;
import lombok.experimental.*;

@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public abstract class LoadingContext implements ILoadingContext<IParaThing> {
    @NonNull
    protected final IParaLoaderRegistry loaderRegistry;
    @Getter
    @NonNull
    protected final IParaLoader<IParaThing> loader;

    @Override
    public IParaRegistry registry() {
        return loaderRegistry.registry();
    }
}
