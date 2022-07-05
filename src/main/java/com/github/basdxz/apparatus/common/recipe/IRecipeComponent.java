package com.github.basdxz.apparatus.common.recipe;

import com.github.basdxz.apparatus.common.registry.IEntityID;

public interface IRecipeComponent {
    IEntityID paraID();

    IRecipeComponentType type();

    int quantity();
}
