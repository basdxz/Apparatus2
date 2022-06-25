package com.github.basdxz.apparatus.tiger;

import com.falsepattern.lib.util.LangUtil;
import com.github.basdxz.apparatus.common.parathing.IParaItem;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ParaItemAdapter extends Item {
    protected final IParaItem paraItem;
    protected final ParaItemRendererAdapter renderer;

    public ParaItemAdapter(@NonNull IParaItem paraItem) {
        this.paraItem = paraItem;
        renderer = new ParaItemRendererAdapter(paraItem.renderers());

        setUnlocalizedName("apparatus." + paraItem.paraID());
        MinecraftForgeClient.registerItemRenderer(this, renderer);

        GameRegistry.registerItem(this, paraItem.paraID().paraName());//TODO: modid is fucked up here currently, find a way to better set it?
        LangUtil.defaultLocalization("item.apparatus." + paraItem.paraID() + ".name", paraItem.localizedName());

        //  True if tool
        //  setFull3D();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        renderer.register(iconRegister);
        itemIcon = renderer.fallbackIcon();
    }
}
