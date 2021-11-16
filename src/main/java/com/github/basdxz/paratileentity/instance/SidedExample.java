package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.RegisterParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IFacingHandler;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.val;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Arrays;
import java.util.List;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@RegisterParaTile(modid = MODID, manager = "test_tile")
@Accessors(fluent = true)
@SuperBuilder
public class SidedExample extends ParaTile implements IFacingHandler {
    private final static List<IIcon> icons = Arrays.asList(new IIcon[2]);

    @Getter
    @Setter
    public ForgeDirection facing;

    @Override
    public IParaTile clone() {
        val newTile = (SidedExample) super.clone();
        newTile.facing = DEFAULT_INVENTORY_FACING;
        return newTile;
    }

    @Override
    public void register(IParaTileManager manager) {
        manager.registerTile(builder().tileID("10").build());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons.set(0, iconRegister.registerIcon(MODID + ":test_tile_" + 0));
        icons.set(1, iconRegister.registerIcon(MODID + ":test_tile_" + 1));
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return side == facing ? icons.get(0) : icons.get(1);
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
    public void writeToNBT(NBTTagCompound nbtTag) {
        writeFacingToNBT(nbtTag);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTag) {
        readFacingFromToNBT(nbtTag);
    }
}
