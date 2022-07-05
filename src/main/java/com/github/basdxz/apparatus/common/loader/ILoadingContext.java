package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.domain.IEntity;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;

public interface ILoadingContext<T extends IEntity> {
    IParaRegistry registry();

    IEntityLoader<T> loader();
}
