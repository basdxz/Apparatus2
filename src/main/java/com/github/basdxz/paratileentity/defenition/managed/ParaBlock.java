package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
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

import java.util.ArrayList;
import java.util.List;

import static com.github.basdxz.paratileentity.defenition.IParaTileManager.NULL_TILE_ID;

@Getter
@Accessors(fluent = true)
public class ParaBlock extends BlockContainer implements IParaBlock, ICarvable {
    protected final IParaTileManager manager;

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

    // FIXME: FLAT_FIX
    @Override
    public IIcon getIcon(int side, int tileID) {
        // return proxiedBlock().getIcon(ForgeDirection.getOrientation(side));
        return proxiedBlock(NULL_TILE_ID).getIcon(ForgeDirection.getOrientation(side));
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int posX, int posY, int posZ, int tileID, int fortune) {
        return manager().bufferedTile().paraTile().getDrops(fortune);
    }

    // FIXME: FLAT_FIX
    @Override
    public int getDamageValue(World world, int posX, int posY, int posZ) {
        //return tileID(world, posX, posY, posZ);
        return 0;
    }

    // FIXME: FLAT_FIX
    // region CHISEL
    //@Override
    //public int getRenderType() {
    //    return ClientUtils.renderCTMId;
    //}

    // FIXME: FLAT_FIX
    @Override
    public IVariationInfo getManager(IBlockAccess blockAccess, int posX, int posY, int posZ, int blockMeta) {
        //return manager.carvingHelper().getVariation(tileID(blockAccess, posX, posY, posZ));
        return manager.carvingHelper().getVariation(0);
    }

    // FIXME: FLAT_FIX
    @Override
    public IVariationInfo getManager(int tileID) {
        return manager.carvingHelper().getVariation(tileID);
    }
    //endregion

    @Override
    public void breakBlock(World world, int posX, int posY, int posZ, Block block, int metaBlock) {
        paraTile(world, posX, posY, posZ).breakBlock();
        super.breakBlock(world, posX, posY, posZ, block, metaBlock);
    }
}
