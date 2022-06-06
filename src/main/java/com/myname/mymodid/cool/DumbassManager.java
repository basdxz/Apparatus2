package com.myname.mymodid.cool;

import com.myname.mymodid.util.LangUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.item.Item;

public class DumbassManager {
    public static void register(@NonNull IParaItem paraItem) {
        val item = new Item();
        item.setUnlocalizedName("modid." + paraItem.paraID());
        GameRegistry.registerItem(item, paraItem.paraID());
        LangUtil.defaultLocalization(paraItem.localizedName(), "modid." + paraItem.paraID());
    }
}
