package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.loader.IInitContext;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
class InitContext implements IInitContext<IParaThing> {
    @NonNull
    protected final IParaManager registry;
}
