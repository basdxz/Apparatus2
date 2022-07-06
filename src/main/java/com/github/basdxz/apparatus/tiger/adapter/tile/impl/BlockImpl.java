package com.github.basdxz.apparatus.tiger.adapter.tile.impl;

import com.falsepattern.lib.util.LangUtil;
import com.github.basdxz.apparatus.common.entity.ITile;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockImpl extends Block {
    protected final ITile paraBlock;

    public BlockImpl(@NonNull ITile paraBlock) {
        super(Material.iron); //TODO: IDK, what should this even be??
        this.paraBlock = paraBlock;
        setBlockName("apparatus." + paraBlock.entityID().entityName());

        GameRegistry.registerBlock(this, ItemBlockImpl.class, paraBlock.entityID().entityName()); //TODO: modid is fucked up here currently, find a way to better set it?
        LangUtil.defaultLocalization("tile.apparatus." + paraBlock.entityID().entityName() + ".name", paraBlock.localizedName());
    }

    @Override
    public void registerBlockIcons(@NonNull IIconRegister iconRegister) {

    }

}
