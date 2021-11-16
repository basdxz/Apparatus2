package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.ParaTileEntityMod;
import com.github.basdxz.paratileentity.defenition.chisel.IChiselRendering;
import com.github.basdxz.paratileentity.defenition.chisel.SubmapActivityMultiManager;
import com.github.basdxz.paratileentity.defenition.tile.IActivityHandler;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.client.render.SubmapMultiManager;
import team.chisel.ctmlib.ISubmapManager;

@Setter
@Getter
@Accessors(fluent = true)
@SuperBuilder
public class ClickableBlockTest extends ParaTile implements IActivityHandler, IChiselRendering {
    private ISubmapManager submapManager;
    private boolean active;

    @Override
    public boolean singleton() {
        return false;
    }

    @Override
    public void updateBlock() {
        super.updateBlock();
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        writeActivityToNBT(nbtTagCompound);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        readActivityFromToNBT(nbtTagCompound);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        registerChiselBlockIcons();
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return getChiselIcon(side);
    }

    @Override
    public ISubmapManager submapManager() {
        if (submapManager == null)
            submapManager = new SubmapActivityMultiManager(SubmapMultiManager.ofGlowCTM("futura/screenCyan"), SubmapMultiManager.ofGlowCTM("futura/screenMetallic"));
        return submapManager;
    }

    @Override
    public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        updateActivity(!active());
        ParaTileEntityMod.info(active() ? "tick!" : "tock");
        return true;
    }
}
