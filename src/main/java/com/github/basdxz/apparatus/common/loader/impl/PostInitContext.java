package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.loader.IParaLoader;
import com.github.basdxz.apparatus.common.loader.IParaLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.IPostInitContext;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import lombok.*;

public class PostInitContext extends LoadingContext implements IPostInitContext<IParaThing> {
    public PostInitContext(@NonNull IParaLoaderRegistry loaderRegistry, @NonNull IParaLoader<IParaThing> loader) {
        super(loaderRegistry, loader);
    }
}
