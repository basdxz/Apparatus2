package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Arrays;
import java.util.List;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@SuperBuilder
public class ParaTile_two extends ParaTile {
    int tier;

    static List<IIcon> icons = Arrays.asList(new IIcon[2]);

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons.set(tier, iconRegister.registerIcon(MODID + ":test_tile_" + tier));
    }

    public IIcon getIcon(ForgeDirection side) {
        return icons.get(tier);
    }

    //@Override
    //public IParaTile clone() {
    //    Random rand = new Random();
    //    int int_random = rand.nextInt(200);
    //    val para = (ParaTile_two) super.clone();
    //    para.tier = int_random;
    //    return para;
    //}

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        if (tier == 1) {
            tier = 0;
        } else {
            tier = 1;
        }

        System.out.println("HELLOW from ParaTile 2!!");
        System.out.println("tier: " + tier);
    }

    @Override
    public boolean singleton() {
        return false;
    }
}
