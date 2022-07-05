package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.domain.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IEntityLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.ILoadingContext;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;
import lombok.experimental.*;

@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public abstract class LoadingContext implements ILoadingContext<IEntity> {
    @NonNull
    protected final IEntityLoaderRegistry loaderRegistry;
    @Getter
    @NonNull
    protected final IEntityLoader<IEntity> loader;

    @Override
    public IParaRegistry registry() {
        return loaderRegistry.registry();
    }
}
