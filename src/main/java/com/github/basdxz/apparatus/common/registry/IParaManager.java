package com.github.basdxz.apparatus.common.registry;

import com.github.basdxz.apparatus.common.domain.IEntity;
import com.github.basdxz.apparatus.common.loader.IInitializeable;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

public interface IParaManager extends IParaRegistry, IInitializeable {
    String loadersPackage();

    void register(@NonNull IEntity paraThing);

    void register(@NonNull IRecipe recipe);
}
