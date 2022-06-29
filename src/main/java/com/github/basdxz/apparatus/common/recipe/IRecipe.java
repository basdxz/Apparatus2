package com.github.basdxz.apparatus.common.recipe;

import java.util.List;

public interface IRecipe {
    IRecipeType type();

    List<IRecipeIngredient> ingredients();

    List<IRecipeResult> results();

    List<IRecipeRequirements> requirements();
}
