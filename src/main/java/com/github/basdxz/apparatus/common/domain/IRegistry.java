package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

public interface IRegistry {
    default boolean registryEquals(@NonNull IRegistry registry) {
        return registryName().equals(registry.registryName());
    }

    default String registryToString() {
        return registryName();
    }

    String registryName();

    void register(@NonNull IEntity entity);

    void register(@NonNull IRecipe recipe);
}
