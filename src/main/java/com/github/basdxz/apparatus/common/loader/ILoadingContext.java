package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;

public interface ILoadingContext<T extends IParaThing> {
    IParaRegistry registry();

    IParaLoader<T> loader();
}
