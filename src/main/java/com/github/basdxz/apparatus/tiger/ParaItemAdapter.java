package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
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

        //  True if tool
        //  setFull3D();
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        renderer.register(iconRegister);
        itemIcon = renderer.fallbackIcon();
    }
}
