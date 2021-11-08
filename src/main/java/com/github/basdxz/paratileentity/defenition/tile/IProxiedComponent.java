package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IProxiedComponent {
    int tileID();

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
