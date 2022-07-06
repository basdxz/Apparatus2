package com.github.basdxz.apparatus.tiger.adapter.fluid.impl;

import com.github.basdxz.apparatus.common.entity.IFluid;
import com.github.basdxz.apparatus.tiger.adapter.fluid.IFluidAdapter;
import lombok.*;
import lombok.experimental.*;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

@Getter
@Accessors(fluent = true, chain = true)
public class FluidAdapter implements IFluidAdapter {
    protected final IFluid fluid;
    protected final Fluid forgeFluid;
    protected final BlockFluidBase fluidBlock;

    public FluidAdapter(@NonNull IFluid fluid) {
        this.fluid = fluid;
        forgeFluid = newForgeFluid();
        fluidBlock = newFluidBlock();
    }

    protected Fluid newForgeFluid() {
        return new FluidImpl(this);
    }

    protected BlockFluidBase newFluidBlock() {
        return new BlockFluidClassicImpl(this, FluidItemBlockImpl.class);
    }
}
