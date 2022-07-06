package com.github.basdxz.apparatus.common.loader.context.impl;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IInternalDomainLoader;
import com.github.basdxz.apparatus.common.loader.context.IInitContext;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

import java.util.List;

public class InitContext extends LoadingContext implements IInitContext<IEntity> {
    public InitContext(@NonNull IInternalDomainLoader domainLoader, @NonNull IEntityLoader<IEntity> entityLoader) {
        super(domainLoader, entityLoader);
    }

    @Override
    public List<IEntity> registered() {
        return domainLoader.registeredEntities(entityLoader);
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        domainLoader.register(this, recipe);
    }
}
