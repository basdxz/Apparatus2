package com.github.basdxz.apparatus.util;

import cpw.mods.fml.common.registry.GameRegistry;
import lombok.val;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.github.basdxz.apparatus.instance.ParaTileEntity.MANAGER;

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
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 8; k = k + 2) {
                        MANAGER.paraTile("wogi").placeInWorld(world, posX + i, posY + k, posZ + j);
                    }
                }
            }
        }
        return super.onItemRightClick(itemStack, world, entityPlayer);
    }
}
