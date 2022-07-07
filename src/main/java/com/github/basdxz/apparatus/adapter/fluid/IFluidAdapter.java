package com.github.basdxz.apparatus.adapter.fluid;

import com.github.basdxz.apparatus.adapter.tile.ITileAdapter;
import com.github.basdxz.apparatus.common.entity.IFluid;
import com.github.basdxz.apparatus.common.entity.ITile;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

public interface IFluidAdapter extends ITileAdapter {
    @Override
    default ITile tile() {
        return fluid();
    }

    @Override
    default Block block() {
        return fluidBlock();
    }

    @Override
    default ItemBlock itemBlock() {
        return fluidItem();
    }

    IFluid fluid();

    Fluid forgeFluid();

    BlockFluidBase fluidBlock();

    ItemBlock fluidItem();
}
