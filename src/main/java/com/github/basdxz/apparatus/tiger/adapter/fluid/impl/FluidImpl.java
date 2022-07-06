package com.github.basdxz.apparatus.tiger.adapter.fluid.impl;

import com.github.basdxz.apparatus.tiger.adapter.fluid.IFluidAdapter;
import lombok.*;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidImpl extends Fluid {
    public FluidImpl(@NonNull IFluidAdapter fluidAdapter) {
        super(fluidAdapter.fluid().entityName());
        FluidRegistry.registerFluid(this);
    }
}
