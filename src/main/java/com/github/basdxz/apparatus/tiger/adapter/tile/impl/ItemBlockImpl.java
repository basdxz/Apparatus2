package com.github.basdxz.apparatus.tiger.adapter.tile.impl;

import com.github.basdxz.apparatus.tiger.ParaItemRendererAdapter;
import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.MinecraftForgeClient;

public class ItemBlockImpl extends ItemBlock {
    protected final ParaItemRendererAdapter renderer;

    public ItemBlockImpl(@NonNull Block block) {
        super(block);

        if (!(block instanceof BlockImpl))
            throw new IllegalArgumentException("Use only with adapter item things");//TODO: better exception

        val paraBlockAdapter = (BlockImpl) block;
        renderer = new ParaItemRendererAdapter(paraBlockAdapter.paraBlock.renderers());
        MinecraftForgeClient.registerItemRenderer(this, renderer);
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        renderer.register(iconRegister);
        itemIcon = renderer.fallbackIcon();
    }
}
