package com.github.basdxz.paratileentity.defenition.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IProxiedItemBlock {
    String getUnlocalizedName(ItemStack itemStack);

    void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced);
}
