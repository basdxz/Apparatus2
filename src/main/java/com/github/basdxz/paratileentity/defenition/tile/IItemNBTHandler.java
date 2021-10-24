package com.github.basdxz.paratileentity.defenition.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IItemNBTHandler {
    default ItemStack writeNbtNewItemStack(ItemStack itemStack) {
        return writeNBTToItemStack(itemStack);
    }

    default ItemStack writeNBTToItemStack(ItemStack itemStack) {
        if (!singleton()) {
            if (itemStack.stackTagCompound == null)
                itemStack.stackTagCompound = new NBTTagCompound();
            writeToNBT(itemStack.stackTagCompound);
        }
        return itemStack;
    }

    default void readNBTOnBlockPlacedBy(ItemStack itemStack) {
        readNBTFromItemStack(itemStack);
    }

    default void readNBTFromItemStack(ItemStack itemStack) {
        if (!singleton() && (itemStack.stackTagCompound != null))
            readFromNBT(itemStack.stackTagCompound);
    }

    boolean singleton();

    void writeToNBT(NBTTagCompound tagCompound);

    void readFromNBT(NBTTagCompound tagCompound);
}
