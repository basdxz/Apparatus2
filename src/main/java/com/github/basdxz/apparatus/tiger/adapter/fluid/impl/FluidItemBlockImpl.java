package com.github.basdxz.apparatus.tiger.adapter.fluid.impl;

import com.github.basdxz.apparatus.tiger.adapter.fluid.IFluidAdapter;
import com.github.basdxz.apparatus.tiger.adapter.fluid.IFluidBlockImpl;
import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class FluidItemBlockImpl extends ItemBlock {
    protected final IFluidAdapter fluidAdapter;

    public FluidItemBlockImpl(@NonNull Block block) {
        super(block);
        ensureValidBlock(block);
        fluidAdapter = ((IFluidBlockImpl) block).fluidAdapter();
    }

    protected void ensureValidBlock(@NonNull Block block) {
        if (!(block instanceof IFluidBlockImpl))
            throw new IllegalArgumentException("Block must implement IFluidBlockImpl"); //TODO: Better exceptions
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName();
    }

    @Override
    public String getUnlocalizedName() {
        return fluidAdapter.unlocalizedName();
    }
}
