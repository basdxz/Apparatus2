package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.context.IInitContext;
import com.github.basdxz.apparatus.common.loader.context.IPostInitContext;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import lombok.*;

public interface IEntityLoader<T extends IEntity> {
    void preInit(@NonNull IPreInitContext<T> context);

    void init(@NonNull IInitContext<T> context);

    void postInit(@NonNull IPostInitContext<T> context);
}