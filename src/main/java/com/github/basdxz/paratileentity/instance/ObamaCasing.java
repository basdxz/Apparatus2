package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@SuperBuilder
public class ObamaCasing extends ParaTile {
    private static IIcon[] icons = new IIcon[8];

    private final int casingID; // just goes from 0-7 as proof-of-concept, string names will follow.

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        if (icons[0] != null)
            return;
        icons[0] = iconRegister.registerIcon(MODID + ":obama/Abrasion");
        icons[1] = iconRegister.registerIcon(MODID + ":obama/Impact");
        icons[2] = iconRegister.registerIcon(MODID + ":obama/Impact2");
        icons[3] = iconRegister.registerIcon(MODID + ":obama/Impact32");
        icons[4] = iconRegister.registerIcon(MODID + ":obama/TitaniumThermal");
        icons[5] = iconRegister.registerIcon(MODID + ":obama/TitaniumThermal32");
        icons[6] = iconRegister.registerIcon(MODID + ":obama/Watertight");
        icons[7] = iconRegister.registerIcon(MODID + ":obama/Watertight32");
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return icons[casingID];
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + "." + getName() + "_casing";
    }

    protected String getName() {
        switch (casingID) {
            case 0:
                return "abrasion";
            case 1:
                return "impact";
            case 2:
                return "impact_2";
            case 3:
                return "impact_32";
            case 4:
                return "titanium_thermal";
            case 5:
                return "titanium_thermal_32";
            case 6:
                return "watertight";
            case 7:
                return "watertight_32";
            default:
                return "unknown";
        }
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced) {
        toolTip.add(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.ITALIC +
                StatCollector.translateToLocal(getUnlocalizedName(itemStack) + ".desc"));
    }
}
