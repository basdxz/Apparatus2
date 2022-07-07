package com.github.basdxz.apparatus.adapter.fluid.impl;

import com.github.basdxz.apparatus.adapter.fluid.IFluidAdapter;
import lombok.*;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidImpl extends Fluid {
    protected final IFluidAdapter fluidAdapter;

    public FluidImpl(@NonNull IFluidAdapter fluidAdapter) {
        super(fluidAdapter.fluid().entityName());
        this.fluidAdapter = fluidAdapter;
        register();
    }

    public void register() {
        FluidRegistry.registerFluid(this);
    }
}
