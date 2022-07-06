package com.github.basdxz.apparatus.common.loader.context.impl;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IInternalDomainLoader;
import com.github.basdxz.apparatus.common.loader.context.IPostInitContext;
import lombok.*;

public class PostInitContext extends LoadingContext implements IPostInitContext<IEntity> {
    public PostInitContext(@NonNull IInternalDomainLoader domainLoader, @NonNull IEntityLoader<IEntity> entityLoader) {
        super(domainLoader, entityLoader);
    }
}
