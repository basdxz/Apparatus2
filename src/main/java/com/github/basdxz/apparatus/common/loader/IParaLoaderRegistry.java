package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import lombok.*;

import java.util.List;

public interface IParaLoaderRegistry extends IInitializeable {
    IParaRegistry registry();

    void register(@NonNull IPreInitContext<IParaThing> context, @NonNull IParaThing paraThing);

    List<IParaThing> loadedParaThings(@NonNull IParaLoader<IParaThing> loader);

    void register(@NonNull IRecipe recipe);
}
