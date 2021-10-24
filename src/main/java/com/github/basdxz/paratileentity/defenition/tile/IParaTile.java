package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import net.minecraft.item.ItemStack;

public interface IParaTile extends Cloneable, IProxiedBlock, IProxiedItemBlock, IProxiedTileEntity {
    default ItemStack newItemStack() {
        return new ItemStack(manager().paraBlock().block(), 1, tileID());
    }

    int tileID();

    IParaTile manager(IParaTileManager manager);

    IParaTileManager manager();

    IParaTile clone();
}
