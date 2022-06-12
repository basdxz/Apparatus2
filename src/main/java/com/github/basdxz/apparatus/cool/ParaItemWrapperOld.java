package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ParaItemWrapperOld extends Item {
    protected final IParaItem paraItem;
    protected final ParaItemRendererWrapperOld renderer;//TODO: CLIENT SIDE ONLY!

//    public IIcon inner;
//    public IIcon outer;
//    private IIcon geauh;
//    private Model prism;

    public ParaItemWrapperOld(@NonNull IParaItem paraItem) {
        this.paraItem = paraItem;
        setUnlocalizedName("apparatus." + paraItem.paraID());
        setTextureName("apparatus:SwInner");

//        setFull3D(); //This is for stuff like tools, makes the item bigger in third-person and orients it like a tool/sword
        renderer = new ParaItemRendererWrapperOld();//TODO: CLIENT SIDE ONLY!
        MinecraftForgeClient.registerItemRenderer(this, renderer);//TODO: CLIENT SIDE ONLY!
    }

    //TODO: CLIENT SIDE ONLY!
    @Override
    public void registerIcons(@NonNull IIconRegister register) {
        super.registerIcons(register);
//        inner = register.registerIcon("apparatus:SwInner");
//        outer = register.registerIcon("apparatus:Swouter");
//        geauh = register.registerIcon("apparatus:Geauh");
//        prism = new Model("apparatus", "models/prism", "models/prism.png");

        renderer.registerResources(register);
    }
}
