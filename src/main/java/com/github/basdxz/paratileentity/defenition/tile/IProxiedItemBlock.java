package com.github.basdxz.paratileentity.defenition.tile;

import lombok.val;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.PARA_TILE_ID_INT_NBT_TAG;

/*
   TODO: Copy across "Proxied" functions into their own interface.
   TODO: List all ItemBlock functions then Implement and pass through the functions with defaults provided here.
 */
public interface IProxiedItemBlock extends IProxiedComponent {
    default String getUnlocalizedName(ItemStack itemStack) {
        return "tile." + manager().modid() + "." + manager().name();
    }

    default void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced) {
    }

    default boolean placeBlockAt(ItemStack itemStack, EntityPlayer entityPlayer, World world,
                                 int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (placeInWorld(world, posX, posY, posZ)) {
            loadParaTile(world, posX, posY, posZ);
            manager().block().onBlockPlacedBy(world, posX, posY, posZ, entityPlayer, itemStack);
            manager().block().onPostBlockPlaced(world, posX, posY, posZ, 0);
            return true;
        } else {
            return false;
        }
    }

    default ItemStack newItemStack(int count) {
        val itemStack = new ItemStack(manager().block(), count);
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setString(PARA_TILE_ID_INT_NBT_TAG, tileID());
        return itemStack;
    }
}
