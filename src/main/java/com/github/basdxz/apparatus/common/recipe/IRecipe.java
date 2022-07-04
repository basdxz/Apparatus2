package com.github.basdxz.apparatus.common.recipe;

import java.util.List;

public interface IRecipe {
    IRecipeType type();

    List<IRecipeComponent> ingredients();

    List<IRecipeComponent> results();

    List<IRecipeRequirement> requirements();
}
