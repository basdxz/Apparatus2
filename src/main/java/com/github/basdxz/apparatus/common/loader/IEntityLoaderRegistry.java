package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.domain.IEntity;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;

import java.util.List;

public interface IEntityLoaderRegistry extends IInitializeable {
    IParaRegistry registry();

    void register(@NonNull IPreInitContext<IEntity> context, @NonNull IEntity paraThing);

    List<IEntity> loadedParaThings(@NonNull IEntityLoader<IEntity> loader);

    void register(@NonNull IRecipe recipe);
}
