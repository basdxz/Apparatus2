package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.parathing.ParaItem;
import com.github.basdxz.apparatus.util.LangUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.item.Item;

public class DumbassManager {
    public static void register(@NonNull ParaItem paraItem) {
        val item = new Item();
        item.setUnlocalizedName("modid." + paraItem.paraID());
        GameRegistry.registerItem(item, paraItem.paraID());
        LangUtil.defaultLocalization("item.modid." + paraItem.paraID() + ".name", paraItem.localizedName());
    }
}
