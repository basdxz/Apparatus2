package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.IFacingHandler;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Arrays;
import java.util.List;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@Getter
@Setter
@Accessors(fluent = true)
@SuperBuilder
public class SidedExample extends ParaTile implements IFacingHandler {
    public ForgeDirection facing;

    static List<IIcon> icons = Arrays.asList(new IIcon[2]);

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons.set(0, iconRegister.registerIcon(MODID + ":test_tile_" + 0));
        icons.set(1, iconRegister.registerIcon(MODID + ":test_tile_" + 1));
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        if (facing == null)
            facing = DEFAULT_INVENTORY_FACING;
        if (side == facing)
            return icons.get(0);
        return icons.get(1);
    }

    @Override
    public void onBlockPlacedBy(EntityLivingBase entityLivingBase, ItemStack itemStack) {
        facingOnBlockPlacedBy(entityLivingBase);
    }

    @Override
    public boolean singleton() {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        writeFacingToNBT(nbtTagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        readFacingFromToNBT(nbtTagCompound);
    }
}
