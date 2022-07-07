package com.github.basdxz.apparatus.tiger.adapter.item.impl;

import com.falsepattern.lib.util.LangUtil;
import com.github.basdxz.apparatus.tiger.adapter.item.IItemAdapter;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemImpl extends Item {
    protected final IItemAdapter itemAdapter;

    public ItemImpl(@NonNull IItemAdapter itemAdapter) {
        this.itemAdapter = itemAdapter;
        initName();
        register();

        //  True if tool
        //  setFull3D();
    }

    protected void initName() {
        val item = itemAdapter.item();
        val entityID = item.entityID();
        val domainName = entityID.domain().domainName();
        val entityName = entityID.entityName();
        setUnlocalizedName(domainName + "." + entityName);
        LangUtil.defaultLocalization("item. " + domainName + "." + entityName + ".name", item.localizedName());
    }

    protected void register() {
        GameRegistry.registerItem(this, itemAdapter.item().entityID().entityName());
    }


    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return getUnlocalizedName();
    }

    @Override
    public String getUnlocalizedName() {
        return itemAdapter.unlocalizedName();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        val itemRenderer = itemAdapter.itemRenderer();
        itemRenderer.register(iconRegister);
        itemIcon = itemRenderer.fallbackIcon();
    }
}
