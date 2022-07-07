package com.github.basdxz.apparatus.tiger.adapter.fluid.impl;

import com.github.basdxz.apparatus.common.entity.IFluid;
import com.github.basdxz.apparatus.tiger.adapter.fluid.IFluidAdapter;
import com.github.basdxz.apparatus.tiger.adapter.render.IItemRendererImpl;
import com.github.basdxz.apparatus.tiger.adapter.render.impl.ItemRendererImpl;
import lombok.*;
import lombok.experimental.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

@Getter
@Accessors(fluent = true, chain = true)
public class FluidAdapter implements IFluidAdapter {
    protected final IFluid fluid;
    protected final Fluid forgeFluid;
    protected final BlockFluidBase fluidBlock;
    protected final ItemBlock fluidItem;
    protected final IItemRendererImpl itemRenderer;

    public FluidAdapter(@NonNull IFluid fluid) {
        this.fluid = fluid;
        forgeFluid = newForgeFluid();
        fluidBlock = newFluidBlock();
        fluidItem = getItemBlock();
        itemRenderer = newItemRenderer();
    }

    protected Fluid newForgeFluid() {
        return new FluidImpl(this);
    }

    protected BlockFluidBase newFluidBlock() {
        return new BlockFluidClassicImpl(this, FluidItemBlockImpl.class);
    }

    protected ItemBlock getItemBlock() {
        return (ItemBlock) Item.getItemFromBlock(fluidBlock);
    }

    protected IItemRendererImpl newItemRenderer() {
        return new ItemRendererImpl(this);
    }
}
