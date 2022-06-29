package com.github.basdxz.apparatus.common.registry;

import com.github.basdxz.apparatus.common.loader.IInitializeable;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import lombok.*;

public interface IParaManager extends IParaRegistry, IInitializeable {
    String loadersPackage();

    void register(@NonNull IParaThing paraThing);
}
