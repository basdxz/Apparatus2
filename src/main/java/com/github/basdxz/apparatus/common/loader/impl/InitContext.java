package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.loader.IInitContext;
import com.github.basdxz.apparatus.common.loader.IParaLoader;
import com.github.basdxz.apparatus.common.loader.IParaLoaderRegistry;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

import java.util.List;

public class InitContext extends LoadingContext implements IInitContext<IParaThing> {
    public InitContext(@NonNull IParaLoaderRegistry loaderRegistry, @NonNull IParaLoader<IParaThing> loader) {
        super(loaderRegistry, loader);
    }

    @Override
    public List<IParaThing> registered() {
        return loaderRegistry.loadedParaThings(loader);
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        loaderRegistry.register(recipe);
    }
}
