package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;

public interface ILoadingContext<T extends IParaThing> {
    IParaRegistry registry();

    interface IPreInit<T extends IParaThing> extends ILoadingContext<T> {
        void register(@NonNull T paraThing);

        IParaID newParaID(@NonNull String paraName);
    }

    interface IInit<T extends IParaThing> extends ILoadingContext<T> {
    }

    interface IPostInit<T extends IParaThing> extends ILoadingContext<T> {
    }
}
