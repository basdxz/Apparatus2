package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

import java.util.List;

public interface IInitContext<T extends IParaThing> extends ILoadingContext<T> {
    List<T> registered();

    void register(@NonNull IRecipe recipe);
}
