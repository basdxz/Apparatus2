package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import net.minecraft.item.ItemStack;

public interface IParaTile extends Cloneable, IProxiedBlock, IProxiedItemBlock, IProxiedTileEntity {
    default ItemStack newItemStack() {
        return new ItemStack(manager().paraBlock().block(), 1, tileID());
    }

    int tileID();

    IParaTileManager manager();

    void registerManager(IParaTileManager manager);

    IParaTile clone();

    void writeNBTToItemStack(ItemStack itemStack);

    void readNBTFromItemStack(ItemStack itemStack);
}
