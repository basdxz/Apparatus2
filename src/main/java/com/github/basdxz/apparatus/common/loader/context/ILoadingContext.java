package com.github.basdxz.apparatus.common.loader.context;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IInternalDomainLoader;

public interface ILoadingContext<T extends IEntity> {
    default IDomain domain() {
        return domainLoader().domain();
    }

    IInternalDomainLoader domainLoader();

    IEntityLoader<T> entityLoader();
}
