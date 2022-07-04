package com.github.basdxz.apparatus.common.recipe.impl;

import com.github.basdxz.apparatus.common.recipe.IRecipeComponent;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponentType;
import com.github.basdxz.apparatus.common.registry.IParaID;
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
    protected final IParaID paraID;
    @NonNull
    protected final IRecipeComponentType type;

    public static IRecipeComponent newRecipeItem(@NonNull IParaID paraID) {
        return new RecipeComponent(paraID, ITEM);
    }

    public static IRecipeComponent newRecipeFluid(@NonNull IParaID paraID) {
        return new RecipeComponent(paraID, FLUID);
    }
}
