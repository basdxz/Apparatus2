package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.loader.IPostInitContext;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
class PostInitContext implements IPostInitContext<IParaThing> {
    @NonNull
    protected final IParaManager registry;
}
