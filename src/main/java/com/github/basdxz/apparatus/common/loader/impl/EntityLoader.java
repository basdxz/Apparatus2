package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.context.IInitContext;
import com.github.basdxz.apparatus.common.loader.context.IPostInitContext;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import lombok.*;

@NoArgsConstructor
public abstract class EntityLoader<T extends IEntity> implements IEntityLoader<T> {
    @Override
    public void preInit(@NonNull IPreInitContext<T> context) {
    }

    @Override
    public void init(@NonNull IInitContext<T> context) {
    }

    @Override
    public void postInit(@NonNull IPostInitContext<T> context) {
    }
}
