package com.github.basdxz.apparatus.common.recipe.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponent;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponentType;
import lombok.*;
import lombok.experimental.*;

import static com.github.basdxz.apparatus.common.recipe.impl.RecipeComponentType.FLUID;
import static com.github.basdxz.apparatus.common.recipe.impl.RecipeComponentType.ITEM;
import static lombok.AccessLevel.PROTECTED;

@Data
@Accessors(fluent = true, chain = true)
@RequiredArgsConstructor(access = PROTECTED)
public class RecipeComponent implements IRecipeComponent {
    @NonNull
    protected final IEntityID entityID;
    @NonNull
    protected final IRecipeComponentType type;
    protected final int quantity;

    public static IRecipeComponent newRecipeItem(@NonNull IEntityID entityID) {
        return new RecipeComponent(entityID, ITEM, 1);
    }

    public static IRecipeComponent newRecipeFluid(@NonNull IEntityID entityID) {
        return new RecipeComponent(entityID, FLUID, 1);
    }
}
