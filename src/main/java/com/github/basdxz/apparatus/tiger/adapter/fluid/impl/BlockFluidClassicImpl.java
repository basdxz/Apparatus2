package com.github.basdxz.apparatus.tiger.adapter.fluid.impl;

import com.github.basdxz.apparatus.tiger.adapter.fluid.IFluidAdapter;
import com.github.basdxz.apparatus.tiger.adapter.fluid.IFluidBlockImpl;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import lombok.experimental.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidClassic;

@Getter
@Accessors(fluent = true, chain = true)
public class BlockFluidClassicImpl extends BlockFluidClassic implements IFluidBlockImpl {
    protected final IFluidAdapter fluidAdapter;

    public BlockFluidClassicImpl(@NonNull IFluidAdapter fluidAdapter,
                                 @NonNull Class<? extends ItemBlock> itemBlockClass) {
        super(fluidAdapter.forgeFluid(), Material.water);
        this.fluidAdapter = fluidAdapter;
        register(itemBlockClass);
    }

    protected void register(@NonNull Class<? extends ItemBlock> itemBlockClass) {
        GameRegistry.registerBlock(this, fluidAdapter.fluid().entityName());
    }
}
