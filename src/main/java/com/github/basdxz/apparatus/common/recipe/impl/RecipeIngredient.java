package com.github.basdxz.apparatus.common.recipe.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponent;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponentType;
import com.github.basdxz.apparatus.common.recipe.IRecipeIngredient;
import com.github.basdxz.apparatus.common.recipe.IRecipeIngredientType;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.recipe.impl.RecipeComponentType.FLUID;
import static com.github.basdxz.apparatus.common.recipe.impl.RecipeComponentType.ITEM;
import static com.github.basdxz.apparatus.common.recipe.impl.RecipeIngredientType.CONSUMABLE;
import static lombok.AccessLevel.PROTECTED;

@Data
@Accessors(fluent = true, chain = true)
@RequiredArgsConstructor(access = PROTECTED)
public class RecipeIngredient implements IRecipeIngredient {
    @NonNull
    protected final IEntityID entityID;
    @NonNull
    protected final IRecipeComponentType type;
    @NonNull
    protected final IRecipeIngredientType ingredientType;
    protected final int quantity;

    public static IRecipeComponent newRecipeItem(@NonNull IEntityID entityID) {
        return new RecipeIngredient(entityID, ITEM, CONSUMABLE, 1);
    }

    public static IRecipeComponent newRecipeFluid(@NonNull IEntityID entityID) {
        return new RecipeIngredient(entityID, FLUID, CONSUMABLE, 1);
    }
}
