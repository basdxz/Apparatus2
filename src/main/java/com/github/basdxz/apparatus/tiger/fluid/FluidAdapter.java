package com.github.basdxz.apparatus.tiger.fluid;

import com.github.basdxz.apparatus.common.entity.IFluid;
import lombok.*;
import lombok.experimental.*;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

@Getter
@Accessors(fluent = true, chain = true)
public class FluidAdapter {
    protected final IFluid fluid;
    protected final Fluid forgeFluid;
    protected final BlockFluidBase fluidBlock;

    public FluidAdapter(@NonNull IFluid fluid) {
        this.fluid = fluid;
        forgeFluid = new FluidImpl(this);
        fluidBlock = new BlockFluidClassicImpl(this);
    }
}
