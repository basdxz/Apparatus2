package com.github.basdxz.paratileentity.defenition.chisel;

import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.github.basdxz.paratileentity.defenition.tile.IActivityHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Builder;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.ctmlib.ISubmapManager;
import team.chisel.ctmlib.RenderBlocksCTM;

public class SubmapActivityMultiManager implements ISubmapManager {
    protected final ISubmapManager active;
    protected final ISubmapManager inactive;
    protected RenderBlocksCTM rendererWrapper;

    @Builder
    public SubmapActivityMultiManager(ISubmapManager active, ISubmapManager inactive) {
        this.active = active;
        this.inactive = inactive;
    }

    @Override
    public void registerIcons(String modName, Block block, IIconRegister register) {
        active.registerIcons(modName, block, register);
        inactive.registerIcons(modName, block, register);
    }

    @Override
    public RenderBlocks createRenderContext(RenderBlocks rendererOld, Block block, IBlockAccess world) {
        initRenderContext();
        return rendererWrapper;
    }

    protected void initRenderContext() {
        if (rendererWrapper == null)
            rendererWrapper = new RenderBlocksWrapper();
    }

    @SideOnly(Side.CLIENT)
    private class RenderBlocksWrapper extends RenderBlocksCTM {
        @Override
        public boolean renderStandardBlock(Block block, int x, int y, int z) {
            manager = active(x, y, z) ? active : inactive;
            boolean ret;
            RenderBlocks renderBlocks = manager.createRenderContext(rendererOld, block, blockAccess);
            renderBlocks.blockAccess = blockAccess;
            if (lockBlockBounds) {
                renderBlocks.overrideBlockBounds(renderMinX, renderMinY, renderMinZ, renderMaxX, renderMaxY, renderMaxZ);
            }
            if (renderBlocks instanceof RenderBlocksCTM) {
                RenderBlocksCTM renderBlocksCTM = (RenderBlocksCTM) renderBlocks;
                renderBlocksCTM.manager = renderBlocksCTM.manager == null ? manager : renderBlocksCTM.manager;
                renderBlocksCTM.rendererOld = renderBlocksCTM.rendererOld == null ? rendererOld : renderBlocksCTM.rendererOld;
            }
            ret = renderBlocks.renderStandardBlock(block, x, y, z);
            renderBlocks.unlockBlockBounds();
            unlockBlockBounds();
            return ret;
        }

        protected boolean active(int x, int y, int z) {
            val tileEntity = Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
            if (!(tileEntity instanceof IParaTileEntity))
                throw new IllegalStateException("TileEntity must implement IParaTileEntity.");
            val paraTile = ((IParaTileEntity) tileEntity).paraTile();
            if (!(paraTile instanceof IActivityHandler))
                throw new IllegalStateException("ParaTile must implement IActivityHandler.");
            return ((IActivityHandler) paraTile).active();
        }

        @Override
        public void renderFaceXNeg(Block block, double x, double y, double z, IIcon icon) {
            getRenderBlocks(block, active).renderFaceXNeg(block, x, y, z, active.getIcon(ForgeDirection.WEST.ordinal(), 0));
        }

        @Override
        public void renderFaceXPos(Block block, double x, double y, double z, IIcon icon) {
            getRenderBlocks(block, active).renderFaceXPos(block, x, y, z, active.getIcon(ForgeDirection.EAST.ordinal(), 0));
        }

        @Override
        public void renderFaceZNeg(Block block, double x, double y, double z, IIcon icon) {
            getRenderBlocks(block, active).renderFaceZNeg(block, x, y, z, active.getIcon(ForgeDirection.NORTH.ordinal(), 0));
        }

        @Override
        public void renderFaceZPos(Block block, double x, double y, double z, IIcon icon) {
            getRenderBlocks(block, active).renderFaceZPos(block, x, y, z, active.getIcon(ForgeDirection.SOUTH.ordinal(), 0));
        }

        @Override
        public void renderFaceYNeg(Block block, double x, double y, double z, IIcon icon) {
            getRenderBlocks(block, active).renderFaceYNeg(block, x, y, z, active.getIcon(ForgeDirection.DOWN.ordinal(), 0));
        }

        @Override
        public void renderFaceYPos(Block block, double x, double y, double z, IIcon icon) {
            getRenderBlocks(block, active).renderFaceYPos(block, x, y, z, active.getIcon(ForgeDirection.UP.ordinal(), 0));
        }

        protected RenderBlocks getRenderBlocks(Block block, ISubmapManager manager) {
            RenderBlocks renderBlocks = manager.createRenderContext(rendererOld, block, blockAccess);
            if (renderBlocks instanceof RenderBlocksCTM)
                ((RenderBlocksCTM) renderBlocks).manager = manager;
            return renderBlocks;
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return inactive.getIcon(side, meta);
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        return inactive.getIcon(world, x, y, z, side);
    }

    @Override
    public void preRenderSide(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    }

    @Override
    public void postRenderSide(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    }
}
