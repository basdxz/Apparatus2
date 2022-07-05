package com.github.basdxz.apparatus.common.recipe;

import java.util.List;
import java.util.Set;

public interface IRecipe {
    IRecipeType type();

    List<IRecipeComponent> inputs();

    default IRecipeComponent output() {
        return outputs().get(0);
    }

    List<IRecipeComponent> outputs();

    Set<IRecipeRequirement> requirements();
}
