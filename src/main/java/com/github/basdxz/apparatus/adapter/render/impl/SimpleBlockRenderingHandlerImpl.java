package com.github.basdxz.apparatus.adapter.render.impl;

import com.github.basdxz.apparatus.adapter.render.ISimpleBlockRenderingHandlerImpl;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class SimpleBlockRenderingHandlerImpl implements ISimpleBlockRenderingHandlerImpl {
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return 0;
    }
}
