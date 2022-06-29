package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import lombok.*;

public interface IParaLoader<T extends IParaThing> {
    default void preInit(@NonNull IPreInitContext<T> context) {
    }

    default void init(@NonNull IInitContext<T> context) {
    }

    default void postInit(@NonNull IPostInitContext<T> context) {
    }
}
