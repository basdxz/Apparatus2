package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import lombok.*;

public interface ILoader<T extends IParaThing> {
    default void preInit(@NonNull ILoadingContext.IPreInit<T> context) {

    }

    default void init(@NonNull ILoadingContext.IInit<T> context) {

    }

    default void postInit(@NonNull ILoadingContext.IPostInit<T> context) {

    }
}
