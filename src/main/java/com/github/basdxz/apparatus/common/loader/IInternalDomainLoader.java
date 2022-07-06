
package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.loader.context.IInitContext;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

import java.util.List;

public interface IInternalDomainLoader extends IDomainLoader {
    IDomain domain();

    void register(@NonNull IPreInitContext<IEntity> preInitContext, @NonNull IEntity entity);

    void register(@NonNull IInitContext<IEntity> postInitContext, @NonNull IRecipe recipe);

    List<IEntity> registeredEntities(@NonNull IEntityLoader<IEntity> entityLoader);
}
