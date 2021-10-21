package com.github.basdxz.paratileentity.defenition.proxied;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ParaBlock extends Block implements IParaBlock {
    @Getter
    protected final IParaTileManager manager;

    public ParaBlock(IParaTileManager manager) {
        super(Material.rock);
        this.manager = manager;
        init(manager.getItemClass(), manager.getName());
    }

    protected void init(Class<? extends ItemBlock> itemClass, String name) {
        setBlockName(name);
        GameRegistry.registerBlock(this, itemClass, getUnlocalizedName());
    }

    @Override
    public Block getBlock() {
        return this;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return manager.createNewTileEntity(world, meta);
    }
}
