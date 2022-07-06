package com.github.basdxz.apparatus.tiger.fluid;

import lombok.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidFinite;

public class BlockFluidFiniteImpl extends BlockFluidFinite {
    public BlockFluidFiniteImpl(@NonNull FluidAdapter fluidAdapter) {
        super(fluidAdapter.forgeFluid, Material.water);
    }
}
