package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.parathing.ParaItem;
import com.github.basdxz.apparatus.util.LangUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;

public class DumbassManager {
    public static void register(@NonNull ParaItem paraItem) {
        val item = new ParaItemWrapper(paraItem);
        GameRegistry.registerItem(item, paraItem.paraID());
        LangUtil.defaultLocalization("item.apparatus." + paraItem.paraID() + ".name", paraItem.localizedName());
    }
}
