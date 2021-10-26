package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedBlock;
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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.api.ICarvable;
import team.chisel.api.carving.IVariationInfo;
import team.chisel.api.rendering.ClientUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public class ParaBlock extends BlockContainer implements IParaBlock, ICarvable {
    protected final IParaTileManager manager;
    protected final ThreadLocal<IProxiedBlock> tempProxiedBlock = new ThreadLocal<>();

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
        return manager.createNewTileEntity(world, tileID);
    }

    @SuppressWarnings("unchecked") // Unavoidable due to Minecraft providing a raw list.
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item block, CreativeTabs creativeTabs, List subBlocks) {
        for (val tileID : manager.allTileIDs())
            subBlocks.add(new ItemStack(block, 1, tileID));
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        for (val tile : manager.tileList())
            tile.registerBlockIcons(iconRegister);
        manager.carvingHelper().registerBlockIcons(manager().paraBlock().block(), iconRegister);
    }

    @Override
    public void onBlockClicked(World world, int posX, int posY, int posZ, EntityPlayer entityPlayer) {
        proxiedBlock(world, posX, posY, posZ).onBlockClicked(world, posX, posY, posZ, entityPlayer);
    }

    @Override
    public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer entityPlayer, int side, float hitX, float hitY, float hitZ) {
        return proxiedBlock(world, posX, posY, posZ).onBlockActivated(world, posX, posY, posZ, entityPlayer, side, hitX, hitY, hitZ);
    }

    @Override
    public void onBlockPlacedBy(World world, int posX, int posY, int posZ, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        proxiedBlock(world, posX, posY, posZ).onBlockPlacedBy(entityLivingBase, itemStack);
    }

    @Override
    public void onPostBlockPlaced(World world, int posX, int posY, int posZ, int tileID) {
        proxiedBlock(world, posX, posY, posZ).onPostBlockPlaced();
    }

    @Override
    public IIcon getIcon(IBlockAccess blockAccess, int posX, int posY, int posZ, int side) {
        return proxiedBlock(blockAccess, posX, posY, posZ).getIcon(ForgeDirection.getOrientation(side));
    }

    @Override
    public IIcon getIcon(int side, int tileID) {
        return proxiedBlock(tileID).getIcon(ForgeDirection.getOrientation(side));
    }

    @Override
    public void breakBlock(World world, int posX, int posY, int posZ, Block block, int tileID) {
        tempProxiedBlock(world, posX, posY, posZ);
        super.breakBlock(world, posX, posY, posZ, block, tileID);
    }

    protected void tempProxiedBlock(IBlockAccess blockAccess, int posX, int posY, int posZ) {
        tempProxiedBlock.set(proxiedBlock(blockAccess, posX, posY, posZ));
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int posX, int posY, int posZ, int tileID, int fortune) {
        return tempProxiedBlock().getDrops(fortune);
    }

    protected IProxiedBlock tempProxiedBlock() {
        val tempProxiedBlock = this.tempProxiedBlock.get();
        this.tempProxiedBlock.remove();
        return tempProxiedBlock;
    }

    @Override
    public int getDamageValue(World world, int posX, int posY, int posZ) {
        return tileID(world, posX, posY, posZ);
    }

    // region CHISEL
    @Override
    public int getRenderType() {
        return ClientUtils.renderCTMId;
    }

    @Override
    public IVariationInfo getManager(IBlockAccess blockAccess, int posX, int posY, int posZ, int tileID) {
        return manager.carvingHelper().getVariation(tileID);
    }

    @Override
    public IVariationInfo getManager(int tileID) {
        return manager.carvingHelper().getVariation(tileID);
    }
    //endregion
}
