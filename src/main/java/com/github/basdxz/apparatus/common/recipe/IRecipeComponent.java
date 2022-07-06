package com.github.basdxz.apparatus.common.recipe;

import com.github.basdxz.apparatus.common.domain.IEntityID;

public interface IRecipeComponent {
    IEntityID entityID();

    IRecipeComponentType type();

    int quantity();
}
