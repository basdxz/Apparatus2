package com.github.basdxz.apparatus.tiger.fluid;

import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockFluidClassicImpl extends BlockFluidClassic {
    public BlockFluidClassicImpl(@NonNull FluidAdapter fluidAdapter) {
        super(fluidAdapter.forgeFluid, Material.water);
        GameRegistry.registerBlock(this, fluidAdapter.fluid.entityName());
    }
}
