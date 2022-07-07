package com.github.basdxz.apparatus.adapter.fluid;

import com.github.basdxz.apparatus.adapter.tile.IBlockImpl;
import com.github.basdxz.apparatus.adapter.tile.ITileAdapter;

public interface IFluidBlockImpl extends IBlockImpl {
    @Override
    default ITileAdapter tileAdapter() {
        return fluidAdapter();
    }

    IFluidAdapter fluidAdapter();
}
