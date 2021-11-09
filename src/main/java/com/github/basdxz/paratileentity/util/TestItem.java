package com.github.basdxz.paratileentity.util;

import cpw.mods.fml.common.registry.GameRegistry;
import lombok.var;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import static com.github.basdxz.paratileentity.instance.ParaTileEntity.MANAGER;

public class TestItem extends ItemShears {
    public TestItem() {
        GameRegistry.registerItem(this, this.getUnlocalizedName());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        if (!p_77659_2_.isRemote) {
            var i = 1;
            p_77659_2_.setBlock((int) p_77659_3_.posX, (int) p_77659_3_.posY + i++, (int) p_77659_3_.posZ, MANAGER.block(), 0, 3);
            p_77659_2_.setBlock((int) p_77659_3_.posX, (int) p_77659_3_.posY + i++, (int) p_77659_3_.posZ, MANAGER.block(), 0, 3);
            p_77659_2_.setBlock((int) p_77659_3_.posX, (int) p_77659_3_.posY + i++, (int) p_77659_3_.posZ, MANAGER.block(), 0, 3);
            p_77659_2_.setBlock((int) p_77659_3_.posX, (int) p_77659_3_.posY + i++, (int) p_77659_3_.posZ, MANAGER.block(), 0, 3);
            p_77659_2_.setBlock((int) p_77659_3_.posX, (int) p_77659_3_.posY + i++, (int) p_77659_3_.posZ, MANAGER.block(), 0, 3);
        }
        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }
}
