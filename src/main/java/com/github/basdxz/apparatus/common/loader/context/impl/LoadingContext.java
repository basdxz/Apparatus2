package com.github.basdxz.apparatus.common.loader.context.impl;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IInternalDomainLoader;
import com.github.basdxz.apparatus.common.loader.context.ILoadingContext;
import lombok.*;
import lombok.experimental.*;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public abstract class LoadingContext implements ILoadingContext<IEntity> {
    @NonNull
    protected final IInternalDomainLoader domainLoader;
    @NonNull
    protected final IEntityLoader<IEntity> entityLoader;
}
