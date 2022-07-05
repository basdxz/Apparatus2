package com.github.basdxz.apparatus.common.recipe;

import com.github.basdxz.apparatus.common.registry.IParaID;

public interface IRecipeComponent {
    IParaID paraID();

    IRecipeComponentType type();

    int quantity();
}
