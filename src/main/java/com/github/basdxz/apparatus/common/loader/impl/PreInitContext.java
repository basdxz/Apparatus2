package com.github.basdxz.apparatus.common.loader.impl;

import com.github.basdxz.apparatus.common.loader.IPreInitContext;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import com.github.basdxz.apparatus.common.registry.impl.ParaID;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class PreInitContext implements IPreInitContext<IParaThing> {
    @NonNull
    protected final IParaManager registry;

    @Override
    public void register(@NonNull IParaThing paraThing) {
        ensureValidParaID(paraThing);
        ensureNoDuplicate(paraThing);
        addParaThing(paraThing);
    }

    protected void ensureValidParaID(@NonNull IParaThing paraThing) {
        if (paraThing.paraID() == null)
            throw new IllegalArgumentException("ParaID is null");//TODO: proper exception
        if (!registry().equals(paraThing.paraID().registry()))
            throw new IllegalArgumentException("Registry doesn't match");//TODO: proper exception
    }

    protected void ensureNoDuplicate(@NonNull IParaThing paraThing) {
        if (registry.paraThingsMap().get(paraThing.paraID()) != null)
            throw new IllegalArgumentException("ParaThing already registered");//TODO: proper exception
    }

    protected void addParaThing(@NonNull IParaThing paraThing) {
        registry.paraThingsMap().put(paraThing.paraID(), paraThing);
    }

    @Override
    public IParaID newParaID(@NonNull String paraName) {
        return new ParaID(registry(), paraName);
    }
}
