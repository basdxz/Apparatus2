package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
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
    int BLOCK_UPDATE_FLAG = 1;
    int SEND_TO_CLIENT_FLAG = 2;

    default String getUnlocalizedName(ItemStack itemStack) {
        return "tile." + manager().modid() + "." + manager().name();
    }

    default void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced) {
    }

    // FIXME: FLAT_FIX
    default boolean placeBlockAt(ItemStack itemStack, EntityPlayer entityPlayer, World world,
                                 int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (!world.setBlock(posX, posY, posZ, manager().block(), 0,
                BLOCK_UPDATE_FLAG | SEND_TO_CLIENT_FLAG)) {
            return false;
        }
        if (world.getBlock(posX, posY, posZ) == manager().block()) {
            loadParaTile(itemStack, world, posX, posY, posZ);
            manager().block().onBlockPlacedBy(world, posX, posY, posZ, entityPlayer, itemStack);
            manager().block().onPostBlockPlaced(world, posX, posY, posZ, 0);
        }
        return true;
    }

    default void loadParaTile(ItemStack itemStack, World world, int posX, int posY, int posZ) {
        if (itemStack.stackTagCompound == null)
            return;

        val tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof IParaTileEntity)
            ((IParaTileEntity) tileEntity)
                    .expectedTileID(itemStack.stackTagCompound.getString(PARA_TILE_ID_INT_NBT_TAG))
                    .reloadTileEntity();
    }

    default ItemStack newItemStack(int count) {
        val itemStack = new ItemStack(manager().block(), count);
        itemStack.stackTagCompound = new NBTTagCompound();
        itemStack.stackTagCompound.setString(PARA_TILE_ID_INT_NBT_TAG, tileID());
        return itemStack;
    }
}
