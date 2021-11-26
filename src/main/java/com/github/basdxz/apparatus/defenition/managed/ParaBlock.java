package com.github.basdxz.apparatus.defenition.managed;

import com.github.basdxz.apparatus.ApparatusMod;
import com.github.basdxz.apparatus.defenition.IParaTileManager;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.api.ICarvable;
import team.chisel.api.carving.IVariationInfo;
import team.chisel.api.rendering.ClientUtils;

import java.util.ArrayList;
import java.util.List;

import static com.github.basdxz.apparatus.defenition.IParaTileManager.NULL_TILE_ID;

@Getter
@Accessors(fluent = true)
public class ParaBlock extends BlockContainer implements IParaBlock, ICarvable {
    protected final IParaTileManager manager;
    private IVariationInfo variationCache;

    public ParaBlock(IParaTileManager manager) {
        super(Material.anvil);
        this.manager = manager;
        init(manager.itemClass(), manager.name());
    }

    protected void init(Class<? extends ItemBlock> itemClass, String name) {
        setBlockName(manager.modid() + "." + name);
        setBlockTextureName(manager.modid() + ":" + name);
        setHardness(1.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("wrench", 2);
        setCreativeTab(CreativeTabs.tabBlock);
        GameRegistry.registerBlock(this, itemClass, getUnlocalizedName());
    }

    @Override
    public Block block() {
        return this;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int tileID) {
        return manager.createNewTileEntity();
    }

    @SuppressWarnings("unchecked") // Unavoidable due to Minecraft providing a raw list.
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List subBlocks) {
        for (val paraTile : manager.tileList())
            subBlocks.add(paraTile.newItemStack());
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        for (val tile : manager.tileList())
            tile.registerBlockIcons(iconRegister);
        manager.carvingHelper().registerBlockIcons(manager().block(), iconRegister);
    }

    @Override
    public void onBlockClicked(World world, int posX, int posY, int posZ, EntityPlayer entityPlayer) {
        proxiedBlock(world, posX, posY, posZ).onBlockClicked(entityPlayer);
    }

    @Override
    public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        return proxiedBlock(world, posX, posY, posZ).onBlockActivated(entityPlayer, side, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        proxiedBlock(world, posX, posY, posZ).onBlockPlacedBy(entityLivingBase, itemStack);
    }

    @Override //TODO check if tileID here is actually side?
    public void onPostBlockPlaced(World world, int posX, int posY, int posZ, int tileID) {
        proxiedBlock(world, posX, posY, posZ).onPostBlockPlaced();
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int posX, int posY, int posZ, int side) {
        return proxiedBlock(blockAccess, posX, posY, posZ).getIcon(ForgeDirection.getOrientation(side));
    }

    @Override
    public IIcon getIcon(int side, int blockMeta) {
        if (variationCache != null)
            return variationCache.getIcon(side, blockMeta);
        return manager().paraTile(NULL_TILE_ID).getIcon(ForgeDirection.getOrientation(side));
    }

    // TODO: another mixing for item handling?
    @Override
    public int getRenderBlockPass() {
        ApparatusMod.warn("Invalid use of getRenderBlockPass from:");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace())
            ApparatusMod.warn(stackTraceElement.toString());
        return super.getRenderBlockPass();
    }

    @Override
    public boolean canRenderInPass(int pass) {
        ApparatusMod.warn("Invalid use of canRenderInPass from:");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace())
            ApparatusMod.warn(stackTraceElement.toString());
        return super.canRenderInPass(pass);
    }

    @Override
    public boolean isOpaqueCube() {
        ApparatusMod.warn("Invalid use of isOpaqueCube from:");
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace())
            ApparatusMod.warn(stackTraceElement.toString());
        return super.isOpaqueCube();
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int posX, int posY, int posZ, int side) {
        val direction = ForgeDirection.getOrientation(side);
        return proxiedBlock(blockAccess,
                posX - direction.offsetX, posY - direction.offsetY, posZ - direction.offsetZ)
                .shouldSideBeRendered(posX, posY, posZ, side);
    }

    @Override
    public int getLightOpacity(IBlockAccess blockAccess, int posX, int posY, int posZ) {
        return proxiedBlock(blockAccess, posX, posY, posZ).getLightOpacity();
    }


    @Override
    public ArrayList<ItemStack> getDrops(World world, int posX, int posY, int posZ, int tileID, int fortune) {
        return manager().bufferedTile().paraTile().getDrops(fortune);
    }

    // TODO Have a dedicated passthrough method for this
    @SuppressWarnings("deprecation") // Still used in WAILA API
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return paraTile(world, x, y, z).newItemStack();
    }

    // region CHISEL
    @Override
    public int getRenderType() {
        return ClientUtils.renderCTMId;
    }

    @Override
    public IVariationInfo getManager(IBlockAccess blockAccess, int posX, int posY, int posZ, int blockMeta) {
        return manager.carvingHelper().getVariation(tileID(blockAccess, posX, posY, posZ));
    }

    @Override
    public IVariationInfo getManager(int tileID) {
        variationCache = manager.carvingHelper().getVariation(manager().bufferedTile().tileID());
        return variationCache;
    }
    //endregion

    @Override
    public void breakBlock(World world, int posX, int posY, int posZ, Block block, int metaBlock) {
        paraTile(world, posX, posY, posZ).breakBlock();
        super.breakBlock(world, posX, posY, posZ, block, metaBlock);
    }
}
