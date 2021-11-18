package com.github.basdxz.apparatus.defenition.tile.component;

import com.github.basdxz.apparatus.defenition.managed.IParaTileEntity;
import com.github.basdxz.apparatus.defenition.tile.proxy.IParaItemBlockProxy;
import lombok.val;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

import static com.github.basdxz.apparatus.defenition.managed.IParaTileEntity.PARA_TILE_ID_INT_NBT_TAG;

public interface IParaItemBlockComp extends IParaTileComp, IParaItemBlockProxy {
    int BLOCK_UPDATE_FLAG = 1;
    int SEND_TO_CLIENT_FLAG = 2;

    @Override
    default String getUnlocalizedName(ItemStack itemStack) {
        return "tile." + manager().modid() + "." + manager().name();
    }

    @Override
    default void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced) {
    }

    @Override
    default boolean placeBlockAt(ItemStack itemStack, EntityPlayer entityPlayer, World world,
                                 int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (placeInWorld(world, posX, posY, posZ)) {
            manager().block().onBlockPlacedBy(world, posX, posY, posZ, entityPlayer, itemStack);
            manager().block().onPostBlockPlaced(world, posX, posY, posZ, 0);
            return true;
        } else {
            return false;
        }
    }

    default boolean placeInWorld(World world, int posX, int posY, int posZ) {
        if (!world.setBlock(posX, posY, posZ, manager().block(), 0,
                BLOCK_UPDATE_FLAG | SEND_TO_CLIENT_FLAG))
            return false;

        if (world.getBlock(posX, posY, posZ) == manager().block()) {
            return loadParaTile(world, posX, posY, posZ);
        } else {
            return false;
        }
    }

    default boolean loadParaTile(World world, int posX, int posY, int posZ) {
        val tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof IParaTileEntity) {
            ((IParaTileEntity) tileEntity).loadParaTile(paraTile());
            return true;
        }
        return false;
    }

    default ItemStack newItemStack(int count) {
        val itemStack = new ItemStack(manager().block(), count);
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setString(PARA_TILE_ID_INT_NBT_TAG, tileID());
        return itemStack;
    }
}
