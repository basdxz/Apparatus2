package com.github.basdxz.apparatus.defenition.managed;

import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import com.github.basdxz.apparatus.defenition.tile.proxy.IParaItemBlockProxy;
import lombok.val;
import net.minecraft.item.ItemStack;

import static com.github.basdxz.apparatus.defenition.IParaTileManager.NULL_TILE_ID;
import static com.github.basdxz.apparatus.defenition.managed.IParaTileEntity.PARA_TILE_ID_INT_NBT_TAG;

public interface IParaItemBlock extends IParaManaged {
    default IParaItemBlockProxy proxiedItemBlock(ItemStack itemStack) {
        return paraTile(itemStack).proxiedItemBlock();
    }

    default IParaTile paraTile(ItemStack itemStack) {
        return manager().paraTile(tileID(itemStack));
    }

    //TODO Rework this
    default String tileID(ItemStack itemStack) {
        if (itemStack.stackTagCompound == null)
            return NULL_TILE_ID;

        val tileID = itemStack.stackTagCompound.getString(PARA_TILE_ID_INT_NBT_TAG);
        if (tileID.equals(""))
            return NULL_TILE_ID;
        return tileID;
    }
}
