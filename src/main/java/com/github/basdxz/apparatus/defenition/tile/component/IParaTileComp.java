package com.github.basdxz.apparatus.defenition.tile.component;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IParaTileComp {
    String tileID();

    IParaTileManager manager();

    World worldObj();

    int posX();

    int posY();

    int posZ();

    default ItemStack newItemStack() {
        return newItemStack(1);
    }

    ItemStack newItemStack(int count);
}
