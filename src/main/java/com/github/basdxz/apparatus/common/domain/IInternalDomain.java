package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.resource.IResource;
import lombok.*;

public interface IInternalDomain extends IDomain {
    void register(@NonNull IEntity entity);

    void register(@NonNull IRecipe recipe);

    void register(@NonNull IResource resource);
}
