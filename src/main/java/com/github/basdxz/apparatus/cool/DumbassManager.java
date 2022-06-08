package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.parathing.ParaItem;
import com.github.basdxz.apparatus.util.LangUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraftforge.client.MinecraftForgeClient;

public class DumbassManager {
    public static void register(@NonNull ParaItem paraItem) {
        val item = new ParaItemWrapper(paraItem);
        MinecraftForgeClient.registerItemRenderer(item, new FirstItemRendererThing(item));//TODO: CLIENT SIDE ONLY!
        GameRegistry.registerItem(item, paraItem.paraID());
        LangUtil.defaultLocalization("item.apparatus." + paraItem.paraID() + ".name", paraItem.localizedName());
    }
}
