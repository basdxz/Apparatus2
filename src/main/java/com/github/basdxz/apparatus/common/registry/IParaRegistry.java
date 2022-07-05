package com.github.basdxz.apparatus.common.registry;


import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

import java.util.Optional;

public interface IParaRegistry {
    default String toStringParaRegistry() {
        return domain().domainToString() + ":" + registryName();
    }

    IDomain domain();

    String registryName();

    IEntityID newParaID(@NonNull String paraName);

    Optional<IEntity> paraThing(@NonNull IEntityID paraID);

    Iterable<IEntity> paraThings();

    Iterable<IRecipe> recipes();
}
