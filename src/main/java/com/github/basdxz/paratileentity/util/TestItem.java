package com.github.basdxz.paratileentity.util;

import cpw.mods.fml.common.registry.GameRegistry;
import lombok.val;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.github.basdxz.paratileentity.instance.ParaTileEntity.MANAGER;

public class TestItem extends ItemShears {
    public TestItem() {
        GameRegistry.registerItem(this, getUnlocalizedName());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (!world.isRemote) {
            val posX = (int) entityPlayer.posX;
            val posY = (int) entityPlayer.posY;
            val posZ = (int) entityPlayer.posZ;
            for (int i = 0; i < 60; i++) {
                for (int j = 0; j < 60; j++) {
                    for (int k = 0; k < 120; k = k + 2) {
                        world.setBlock(posX + i, posY + k, posZ + j, MANAGER.block(), 220, 3);
                    }
                }
            }
        }
        return super.onItemRightClick(itemStack, world, entityPlayer);
    }
}
