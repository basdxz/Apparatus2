package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.domain.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IEntityLoaderRegistry;
import com.github.basdxz.apparatus.common.loader.IInitContext;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

import java.util.List;

public class InitContext extends LoadingContext implements IInitContext<IEntity> {
    public InitContext(@NonNull IEntityLoaderRegistry loaderRegistry, @NonNull IEntityLoader<IEntity> loader) {
        super(loaderRegistry, loader);
    }

    @Override
    public List<IEntity> registered() {
        return loaderRegistry.loadedParaThings(loader);
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        loaderRegistry.register(recipe);
    }
}
