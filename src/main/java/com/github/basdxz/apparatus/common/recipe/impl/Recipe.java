package com.github.basdxz.apparatus.common.recipe.impl;

import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponent;
import com.github.basdxz.apparatus.common.recipe.IRecipeRequirement;
import com.github.basdxz.apparatus.common.recipe.IRecipeType;
import lombok.*;
import lombok.experimental.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.github.basdxz.apparatus.common.recipe.impl.RecipeType.SHAPED;
import static com.github.basdxz.apparatus.common.recipe.impl.RecipeType.SHAPELESS;
import static lombok.AccessLevel.PROTECTED;

@Data
@Accessors(fluent = true, chain = true)
@RequiredArgsConstructor(access = PROTECTED)
public class Recipe implements IRecipe {
    @NonNull
    protected final IRecipeType type;
    @NonNull
    protected final List<IRecipeComponent> inputs;
    @NonNull
    protected final List<IRecipeComponent> outputs;
    @NonNull
    protected final Set<IRecipeRequirement> requirements;

    @Builder(builderMethodName = "shapelessBuilder", builderClassName = "shapelessRecipeBuilder")
    protected static IRecipe newShapelessRecipe(
            @Singular @NonNull List<IRecipeComponent> ins,
            @NonNull IRecipeComponent out
    ) {
        return new Recipe(SHAPELESS, ins, Collections.singletonList(out), Collections.emptySet());
    }

    //TODO: Grids other than 3x3
    @Builder(builderMethodName = "shapedBuilder", builderClassName = "shapedRecipeBuilder")
    protected static IRecipe newShapedRecipe(
            @Singular @NonNull List<IRecipeComponent> ins,
            @NonNull IRecipeComponent out
    ) {
        return new Recipe(SHAPED, ins, Collections.singletonList(out), Collections.emptySet());
    }
}
