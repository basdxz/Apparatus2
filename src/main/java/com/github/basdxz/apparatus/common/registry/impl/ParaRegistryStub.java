package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;
import lombok.experimental.*;

import java.util.Collections;
import java.util.Optional;

@Data
@Accessors(fluent = true, chain = true)
public class ParaRegistryStub implements IParaRegistry {
    @NonNull
    protected final IDomain domain;
    @NonNull
    protected final String registryName;

    @Override
    public IEntityID newParaID(@NonNull String paraName) {
//        return new EntityID(this, paraName);//TODO: FIX NOW
        return null;
    }

    @Override
    public Optional<IEntity> paraThing(@NonNull IEntityID paraID) {
        return Optional.empty();
    }

    @Override
    public Iterable<IEntity> paraThings() {
        return Collections.emptyList();
    }

    @Override
    public Iterable<IRecipe> recipes() {
        return Collections.emptyList();
    }
}
