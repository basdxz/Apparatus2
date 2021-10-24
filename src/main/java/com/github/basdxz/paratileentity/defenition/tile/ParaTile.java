package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.BLOCK_UPDATE_FLAG;
import static com.github.basdxz.paratileentity.ParaTileEntityMod.SEND_TO_CLIENT_FLAG;

@Setter
@Getter
@Accessors(fluent = true)
@SuperBuilder
public abstract class ParaTile implements IParaTile {
    protected final int tileID;
    protected IParaTileManager manager;
    protected IParaTileEntity tileEntity;

    @Override
    public IParaTile clone() {
        try {
            return (IParaTile) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Failed to create IParaTile!");
        }
    }

    @Override
    public void onBlockPlacedBy(EntityLivingBase entityLivingBase, ItemStack itemStack) {
    }

    @Override
    public void onPostBlockPlaced() {
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return null;
    }

    @Override
    public ArrayList<ItemStack> getDrops(int fortune) {
        ArrayList<ItemStack> itemDropList = new ArrayList<>();
        itemDropList.add(newItemStack());
        return itemDropList;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return manager.paraBlock().getUnlocalizedName();
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> toolTip,
                               boolean advanced) {
        toolTip.add(itemStack.stackTagCompound.toString());
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer entityPlayer, World world,
                                int posX, int posY, int posZ, int side, float hitX, float hitY, float hitZ) {
        if (!world.setBlock(posX, posY, posZ, manager().paraBlock().block(), tileID,
                BLOCK_UPDATE_FLAG | SEND_TO_CLIENT_FLAG)) {
            return false;
        }
        if (world.getBlock(posX, posY, posZ) == manager().paraBlock()) {
            manager().paraBlock().block().onBlockPlacedBy(world, posX, posY, posZ, entityPlayer, itemStack);
            manager().paraBlock().block().onPostBlockPlaced(world, posX, posY, posZ, tileID);
        }
        return true;
    }

    @Override
    public boolean singleton() {
        return true;
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void updateEntity() {
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
    }


}
