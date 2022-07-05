package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.domain.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IEntityLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.IPostInitContext;
import lombok.*;

public class PostInitContext extends LoadingContext implements IPostInitContext<IEntity> {
    public PostInitContext(@NonNull IEntityLoaderRegistry loaderRegistry, @NonNull IEntityLoader<IEntity> loader) {
        super(loaderRegistry, loader);
    }
}
