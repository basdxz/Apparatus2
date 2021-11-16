package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedItemBlock;
import net.minecraft.item.ItemStack;

public interface IParaItemBlock extends IParaManaged {
    default IProxiedItemBlock proxiedItemBlock(ItemStack itemStack) {
        return paraTile(itemStack);
    }

    default IParaTile paraTile(ItemStack itemStack) {
        return manager().paraTile(tileID(itemStack));
    }

    //FIXME: FLAT_FIX
    default String tileID(ItemStack itemStack) {
        return null;
        //return itemStack.getItemDamage();
    }
}
