package com.github.basdxz.apparatus.tiger.adapter.tile.impl;

import com.falsepattern.lib.util.LangUtil;
import com.github.basdxz.apparatus.tiger.adapter.tile.IBlockImpl;
import com.github.basdxz.apparatus.tiger.adapter.tile.ITileAdapter;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import lombok.experimental.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;

@Getter
@Accessors(fluent = true, chain = true)
public class BlockImpl extends Block implements IBlockImpl {
    protected final ITileAdapter tileAdapter;

    public BlockImpl(@NonNull ITileAdapter tileAdapter,
                     @NonNull Class<? extends ItemBlock> itemBlockClass) {
        super(Material.iron); //TODO: IDK, what should this even be??
        this.tileAdapter = tileAdapter;
        initName();
        register(itemBlockClass);
    }

    protected void register(@NonNull Class<? extends ItemBlock> itemBlockClass) {
        GameRegistry.registerBlock(this, itemBlockClass, tileAdapter.tile().entityName());
    }

    protected void initName() {
        val tile = tileAdapter.tile();
        val entityID = tile.entityID();
        val domainName = entityID.domain().domainName();
        val entityName = entityID.entityName();
        setBlockName(domainName + "." + entityName);
        LangUtil.defaultLocalization("item. " + domainName + "." + entityName + ".name", tile.localizedName());
    }

    //TODO: Note that this will use the terrain tile sheet
    @Override
    public void registerBlockIcons(@NonNull IIconRegister iconRegister) {
    }
}
