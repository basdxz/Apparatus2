package com.github.basdxz.apparatus.defenition.tile.handler;

import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public interface IItemNBTHandler extends IParaTile {
    default void addNBTInformation(ItemStack itemStack, List<String> toolTip) {
        if (itemStack.stackTagCompound != null)
            toolTip.add(itemStack.stackTagCompound.toString());
    }

    default ItemStack writeNbtNewItemStack(ItemStack itemStack) {
        return writeNBTToItemStack(itemStack);
    }

    default ItemStack writeNBTToItemStack(ItemStack itemStack) {
        if (!cloneable()) {
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
        if (!cloneable() && (itemStack.stackTagCompound != null))
            readFromNBT(itemStack.stackTagCompound);
    }
}
