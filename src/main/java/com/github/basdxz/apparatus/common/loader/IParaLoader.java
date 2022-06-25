package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import lombok.*;

public interface IParaLoader<T extends IParaThing> {
    default void preInit(@NonNull IParaLoadingContext.IPreInit<T> context) {

    }

    default void init(@NonNull IParaLoadingContext.IInit<T> context) {

    }

    default void postInit(@NonNull IParaLoadingContext.IPostInit<T> context) {

    }
}
