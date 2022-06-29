package com.github.basdxz.apparatus.common.recipe.impl;

import com.github.basdxz.apparatus.common.recipe.*;
import lombok.*;
import lombok.experimental.*;

import java.util.List;

@Data
@Accessors(fluent = true, chain = true)
public class Recipe implements IRecipe {
    @NonNull
    protected final IRecipeType type;
    @NonNull
    protected final List<IRecipeIngredient> ingredients;
    @NonNull
    protected final List<IRecipeResult> results;
    @NonNull
    protected final List<IRecipeRequirements> requirements;
}
