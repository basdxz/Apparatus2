package com.github.basdxz.apparatus.common.loader;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaID;
import lombok.*;

public interface IPreInitContext<T extends IParaThing> extends ILoadingContext<T> {
    void register(@NonNull T paraThing);

    IParaID newParaID(@NonNull String paraName);
}
