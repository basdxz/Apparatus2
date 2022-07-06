package com.github.basdxz.apparatus.common.loader.context;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IEntity;
import lombok.*;

public interface IPreInitContext<T extends IEntity> extends ILoadingContext<T> {
    void register(@NonNull T entity);

    default IEntityID entityID(@NonNull String entityName) {
        return domain().entityID(entityName);
    }
}
