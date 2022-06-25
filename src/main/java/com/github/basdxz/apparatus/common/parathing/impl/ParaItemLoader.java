package com.github.basdxz.apparatus.common.parathing.impl;

import com.github.basdxz.apparatus.common.loader.IParaLoader;
import com.github.basdxz.apparatus.common.loader.IParaLoadingContext;
import com.github.basdxz.apparatus.common.loader.RegisteredLoader;
import com.github.basdxz.apparatus.common.registry.impl.ParaID;
import lombok.*;

@RegisteredLoader(registryName = "", paraThingClass = ParaItem.class)
public class ParaItemLoader implements IParaLoader<ParaItem> {
    @Override
    public void preInit(@NonNull IParaLoadingContext.IPreInit<ParaItem> context) {
        val id = new ParaID(context.registry(), "woag_my_item");
        context.register(new ParaItem(id, "Woag My Item"));
    }
}
