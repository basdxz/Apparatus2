package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.experimental.SuperBuilder;
import lombok.val;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@SuperBuilder
public class ParaTile_two extends ParaTile {
    int randomNumber;

    static List<IIcon> icons = Arrays.asList(new IIcon[2]);

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons.set(0, iconRegister.registerIcon(MODID + ":test_tile_" + 0));
    }

    public IIcon getIcon(ForgeDirection side) {
        return icons.get(0);
    }

    @Override
    public IParaTile clone() {
        val para = (ParaTile_two) super.clone();
        if (serverSide()) {
            Random rand = new Random();
            para.randomNumber = rand.nextInt(200);
        }
        return para;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        serverSide();
        System.out.println("random: " + randomNumber);
    }

    @Override
    public boolean singleton() {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("tier", randomNumber);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        randomNumber = nbtTagCompound.getInteger("tier");
    }
}
