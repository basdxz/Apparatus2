package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;

public interface IParaLoadingContext<T extends IParaThing> {
    IParaRegistry registry();

    interface IPreInit<T extends IParaThing> extends IParaLoadingContext<T> {
        void register(@NonNull T paraThing);
    }

    interface IInit<T extends IParaThing> extends IParaLoadingContext<T> {
    }

    interface IPostInit<T extends IParaThing> extends IParaLoadingContext<T> {
    }
}
