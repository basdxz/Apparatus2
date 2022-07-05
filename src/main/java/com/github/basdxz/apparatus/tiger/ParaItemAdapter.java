package com.github.basdxz.apparatus.tiger;

import com.falsepattern.lib.util.LangUtil;
import com.github.basdxz.apparatus.common.parathing.IItem;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ParaItemAdapter extends Item {
    protected final IItem paraItem;
    protected final ParaItemRendererAdapter renderer;

    public ParaItemAdapter(@NonNull IItem paraItem) {
        this.paraItem = paraItem;
        renderer = new ParaItemRendererAdapter(paraItem.renderers());

        setUnlocalizedName("apparatus." + paraItem.entityID().paraName());
        MinecraftForgeClient.registerItemRenderer(this, renderer);

        GameRegistry.registerItem(this, paraItem.entityID().paraName());//TODO: modid is fucked up here currently, find a way to better set it?
        LangUtil.defaultLocalization("item.apparatus." + paraItem.entityID().paraName() + ".name", paraItem.localizedName());

        //  True if tool
        //  setFull3D();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        renderer.register(iconRegister);
        itemIcon = renderer.fallbackIcon();
    }
}
