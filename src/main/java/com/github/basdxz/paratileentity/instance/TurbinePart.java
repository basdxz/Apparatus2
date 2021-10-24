package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.val;
import net.minecraft.nbt.NBTTagCompound;

import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.TileEntitySide.SERVER;

@SuppressWarnings("DefaultAnnotationParam") // chain is not false by default as I use fluent, might change it?
@Setter
@Getter
@Accessors(fluent = true, chain = false)
@SuperBuilder
public class TurbinePart extends ParaTile implements ITurbinePart {
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
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger(DURABILITY_NBT_TAG, durability);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        durability = nbtTagCompound.getInteger(DURABILITY_NBT_TAG);
    }
}
