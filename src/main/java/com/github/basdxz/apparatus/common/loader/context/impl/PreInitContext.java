package com.github.basdxz.apparatus.common.loader.context.impl;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IInternalDomainLoader;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import lombok.*;

public class PreInitContext extends LoadingContext implements IPreInitContext<IEntity> {
    public PreInitContext(@NonNull IInternalDomainLoader domainLoader, @NonNull IEntityLoader<IEntity> entityLoader) {
        super(domainLoader, entityLoader);
    }

    @Override
    public void register(@NonNull IEntity entity) {
        domainLoader.register(this, entity);
    }
}
