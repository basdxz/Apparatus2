package com.github.basdxz.paratileentity.instance;

import codechicken.nei.api.API;
import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@SuperBuilder
public class NullTile extends ParaTile {
    protected static IIcon icon;

    @Override
    public void init(@NonNull IParaTileManager manager) {
        super.init(manager);
        if (Loader.isModLoaded("NotEnoughItems"))
            API.hideItem(newItemStack());
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + ".nulltile";
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced) {
        toolTip.add("Things broke, you shouldn't have this.");
        toolTip.add("I hope your day gets better.");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        if (icon == null)
            icon = iconRegister.registerIcon(MODID + ":trolley");
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return icon;
    }
}
