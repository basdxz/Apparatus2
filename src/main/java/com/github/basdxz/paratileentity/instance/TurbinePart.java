package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.IItemNBTHandler;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.val;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.TileEntitySide.SERVER;

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
        if (side() == SERVER)
            durability = maxDurability;
        return para;
    }

    @Override
    public boolean singleton() {
        return false;
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
}