package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedItemBlock;
import lombok.val;
import net.minecraft.item.ItemStack;

import static com.github.basdxz.paratileentity.defenition.IParaTileManager.NULL_TILE_ID;
import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.PARA_TILE_ID_INT_NBT_TAG;

public interface IParaItemBlock extends IParaManaged {
    default IProxiedItemBlock proxiedItemBlock(ItemStack itemStack) {
        return paraTile(itemStack);
    }

    default IParaTile paraTile(ItemStack itemStack) {
        return manager().paraTile(tileID(itemStack));
    }

    default String tileID(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null)
            return NULL_TILE_ID;

        val tileID = itemStack.stackTagCompound.getString(PARA_TILE_ID_INT_NBT_TAG);
        if (tileID.equals(""))
            return NULL_TILE_ID;
        return tileID;
    }
}
