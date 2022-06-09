package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.parathing.ParaItem;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ParaItemWrapper extends Item {
    protected final ParaItem paraItem;
    protected final ParaItemRendererWrapper renderer;//TODO: CLIENT SIDE ONLY!

//    public IIcon inner;
//    public IIcon outer;
//    private IIcon geauh;
//    private Model prism;

    public ParaItemWrapper(@NonNull ParaItem paraItem) {
        this.paraItem = paraItem;
        setUnlocalizedName("apparatus." + paraItem.paraID());
        setTextureName("apparatus:a");

        renderer = new ParaItemRendererWrapper(paraItem);//TODO: CLIENT SIDE ONLY!
        MinecraftForgeClient.registerItemRenderer(this, renderer);//TODO: CLIENT SIDE ONLY!
    }

    //TODO: CLIENT SIDE ONLY!
    @Override
    public void registerIcons(@NonNull IIconRegister register) {
//        super.registerIcons(register);
//        inner = register.registerIcon("apparatus:SwInner");
//        outer = register.registerIcon("apparatus:Swouter");
//        geauh = register.registerIcon("apparatus:Geauh");
//        prism = new Model("apparatus", "models/prism", "models/prism.png");

        renderer.registerResources(register);
    }
}
