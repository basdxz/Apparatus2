package com.github.basdxz.apparatus.common.registry;


import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

import java.util.Optional;

public interface IParaRegistry {
    IDomain domain();

    String registryName();

    IParaID newParaID(@NonNull String paraName);

    Optional<IParaThing> paraThing(@NonNull IParaID paraID);

    Iterable<IParaThing> paraThings();

    Iterable<IRecipe> recipes();
}
