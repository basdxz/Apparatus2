package com.github.basdxz.apparatus.common.parathing.impl;

import com.github.basdxz.apparatus.common.loader.ILoader;
import com.github.basdxz.apparatus.common.loader.ILoadingContext;
import com.github.basdxz.apparatus.common.loader.RegisteredLoader;
import lombok.*;

@NoArgsConstructor
@RegisteredLoader(registryName = "", paraThingClass = ParaItem.class)
public class ParaItemLoader implements ILoader<ParaItem> {
    @Override
    public void preInit(@NonNull ILoadingContext.IPreInit<ParaItem> context) {
        context.register(new ParaItem(context.newParaID("woag_my_item"), "Woag My Item"));
    }
}
