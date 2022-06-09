package com.github.basdxz.apparatus.cool;

import com.falsepattern.dynamicrendering.drawing.Model;
import com.github.basdxz.apparatus.common.parathing.ParaItem;
import lombok.*;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.MinecraftForgeClient;

public class ParaItemWrapper extends Item {
    protected final ParaItem paraItem;
    public IIcon inner;
    public IIcon outer;
    private IIcon geauh;
    private Model prism;

    public ParaItemWrapper(@NonNull ParaItem paraItem) {
        this.paraItem = paraItem;
        setUnlocalizedName("apparatus." + paraItem.paraID());
        setTextureName("apparatus:a");

        MinecraftForgeClient.registerItemRenderer(this, new FirstItemRendererThing(this));//TODO: CLIENT SIDE ONLY!
    }

    @Override
    public void registerIcons(@NonNull IIconRegister register) {
        super.registerIcons(register);
        inner = register.registerIcon("apparatus:SwInner");
        outer = register.registerIcon("apparatus:Swouter");
        geauh = register.registerIcon("apparatus:Geauh");
        prism = new Model("apparatus", "models/prism", "models/prism.png");
    }
}
