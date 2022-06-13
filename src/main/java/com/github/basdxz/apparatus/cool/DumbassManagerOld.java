package com.github.basdxz.apparatus.cool;

import com.falsepattern.lib.util.LangUtil;
import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.tiger.ParaItemAdapter;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;

public class DumbassManagerOld {
    public static void register(@NonNull IParaItem paraItem) {
        val item = new ParaItemAdapter(paraItem);
        GameRegistry.registerItem(item, paraItem.paraID());
        LangUtil.defaultLocalization("item.apparatus." + paraItem.paraID() + ".name", paraItem.localizedName());
    }
}
