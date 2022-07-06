package com.github.basdxz.apparatus.tiger.adapter.fluid;

import com.github.basdxz.apparatus.common.entity.IFluid;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

public interface IFluidAdapter {
    IFluid fluid();

    Fluid forgeFluid();

    BlockFluidBase fluidBlock();
}
