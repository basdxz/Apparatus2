package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.entity.IEntity;
import lombok.*;

public interface IEntityLoader<T extends IEntity> {
    default void preInit(@NonNull IPreInitContext<T> context) {
    }

    default void init(@NonNull IInitContext<T> context) {
    }

    default void postInit(@NonNull IPostInitContext<T> context) {
    }
}
