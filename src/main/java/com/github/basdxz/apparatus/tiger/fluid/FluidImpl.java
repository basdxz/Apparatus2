package com.github.basdxz.apparatus.tiger.fluid;

import lombok.*;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidImpl extends Fluid {
    public FluidImpl(@NonNull FluidAdapter fluidAdapter) {
        super(fluidAdapter.fluid.entityName());
        FluidRegistry.registerFluid(this);
    }
}
