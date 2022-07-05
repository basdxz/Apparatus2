package com.github.basdxz.apparatus.tiger;

import com.falsepattern.lib.util.LangUtil;
import com.github.basdxz.apparatus.common.parathing.ITile;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.MinecraftForgeClient;

public class ParaBlockAdapter extends Block {
    protected final ITile paraBlock;

    public ParaBlockAdapter(@NonNull ITile paraBlock) {
        super(Material.iron); //TODO: IDK, what should this even be??
        this.paraBlock = paraBlock;
        setBlockName("apparatus." + paraBlock.entityID().paraName());

        GameRegistry.registerBlock(this, ParaItemBlockAdapter.class, paraBlock.entityID().paraName()); //TODO: modid is fucked up here currently, find a way to better set it?
        LangUtil.defaultLocalization("tile.apparatus." + paraBlock.entityID().paraName() + ".name", paraBlock.localizedName());
    }

    @Override
    public void registerBlockIcons(@NonNull IIconRegister iconRegister) {

    }

    public static class ParaItemBlockAdapter extends ItemBlock {
        protected final ParaItemRendererAdapter renderer;

        public ParaItemBlockAdapter(@NonNull Block block) {
            super(block);

            if (!(block instanceof ParaBlockAdapter))
                throw new IllegalArgumentException("Use only with adapter item things");//TODO: better exception

            val paraBlockAdapter = (ParaBlockAdapter) block;
            renderer = new ParaItemRendererAdapter(paraBlockAdapter.paraBlock.renderers());
            MinecraftForgeClient.registerItemRenderer(this, renderer);
        }

        @Override
        public void registerIcons(IIconRegister iconRegister) {
            renderer.register(iconRegister);
            itemIcon = renderer.fallbackIcon();
        }
    }
}
