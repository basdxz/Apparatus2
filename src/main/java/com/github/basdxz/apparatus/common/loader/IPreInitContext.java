package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IEntity;
import com.github.basdxz.apparatus.common.registry.IEntityID;
import lombok.*;

public interface IPreInitContext<T extends IEntity> extends ILoadingContext<T> {
    void register(@NonNull T paraThing);

    IEntityID newParaID(@NonNull String paraName);
}
