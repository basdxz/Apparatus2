package com.github.basdxz.apparatus.common.registry.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.registry.IParaID;
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
    public IParaID newParaID(@NonNull String paraName) {
        return new ParaID(this, paraName);
    }

    @Override
    public Optional<IParaThing> paraThing(@NonNull IParaID paraID) {
        return Optional.empty();
    }

    @Override
    public Iterable<IParaThing> paraThings() {
        return Collections.emptyList();
    }

    @Override
    public Iterable<IRecipe> recipes() {
        return Collections.emptyList();
    }
}
