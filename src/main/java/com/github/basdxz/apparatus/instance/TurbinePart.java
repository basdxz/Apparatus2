package com.github.basdxz.apparatus.instance;

import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import com.github.basdxz.apparatus.defenition.tile.ParaTile;
import com.github.basdxz.apparatus.defenition.tile.handler.IItemNBTHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.val;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import team.chisel.shadow.team.chisel.ctmlib.ISubmapManager;

import java.util.List;

@Setter
@Getter
@Accessors(fluent = true)
@SuperBuilder
public class TurbinePart extends ParaTile implements ITurbinePart, IItemNBTHandler {
    private final int maxDurability;
    private final int maxSpeed;
    private int durability;

    @Override
    public IParaTile clone() {
        val para = (TurbinePart) super.clone();
        if (serverSide())
            durability = maxDurability;
        return para;
    }

    @Override
    public boolean cloneable() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip, boolean advanced) {
        addNBTInformation(itemStack, toolTip);
    }

    @Override
    public ItemStack newItemStack() {
        return writeNbtNewItemStack(super.newItemStack());
    }

    @Override
    public void onBlockPlacedBy(EntityLivingBase entityLivingBase, ItemStack itemStack) {
        readNBTOnBlockPlacedBy(itemStack);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger(DURABILITY_NBT_TAG, durability);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        durability = nbtTagCompound.getInteger(DURABILITY_NBT_TAG);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ISubmapManager submapManager() {
        return null;
    }
}
