package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.util.LangUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;

public class DumbassManagerOld {
    public static void register(@NonNull IParaItem paraItem) {
        val item = new ParaItemWrapperOld(paraItem);
        GameRegistry.registerItem(item, paraItem.paraID());
        LangUtil.defaultLocalization("item.apparatus." + paraItem.paraID() + ".name", paraItem.localizedName());
    }
}
