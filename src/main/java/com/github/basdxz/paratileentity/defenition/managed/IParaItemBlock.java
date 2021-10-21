package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedItemBlock;
import net.minecraft.item.ItemStack;

public interface IParaItemBlock extends IParaManaged {
    int BLOCK_UPDATE_FLAG = 1;
    int SEND_TO_CLIENT_FLAG = 2;

    default IProxiedItemBlock getProxiedItemBlock(ItemStack itemStack) {
        return getTile(itemStack);
    }

    default IParaTile getTile(ItemStack itemStack) {
        return getManager().getTile(getTileID(itemStack));
    }

    int getTileID(ItemStack itemStack);
}
