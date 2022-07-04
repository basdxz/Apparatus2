package com.github.basdxz.apparatus.common.recipe.impl;

import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponent;
import com.github.basdxz.apparatus.common.recipe.IRecipeRequirement;
import com.github.basdxz.apparatus.common.recipe.IRecipeType;
import lombok.*;
import lombok.experimental.*;

import java.util.List;

@Data
@Accessors(fluent = true, chain = true)
public class Recipe implements IRecipe {
    @NonNull
    protected final IRecipeType type;
    @NonNull
    protected final List<IRecipeComponent> ingredients;
    @NonNull
    protected final List<IRecipeComponent> results;
    @NonNull
    protected final List<IRecipeRequirement> requirements;
}
