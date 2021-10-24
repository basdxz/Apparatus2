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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@Getter
@Accessors(fluent = true)
public class ParaBlock extends BlockContainer implements IParaBlock {

    @Override
    public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_) {
        super.harvestBlock(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
    }

    protected final IParaTileManager manager;
    protected final ThreadLocal<IProxiedBlock> tempProxiedBlock = new ThreadLocal<>();

    public ParaBlock(IParaTileManager manager) {
        super(Material.anvil);
        this.manager = manager;
        init(manager.itemClass(), manager.name());
    }

    protected void init(Class<? extends ItemBlock> itemClass, String name) {
        setBlockName(MODID + "." + name);
        setBlockTextureName(MODID + ":" + name);
        setHardness(1.0F);
        setResistance(10.0F);
        setStepSound(soundTypeMetal);
        setHarvestLevel("wrench", 2);
        GameRegistry.registerBlock(this, itemClass, getUnlocalizedName());
    }

    @Override
    public Block block() {
        return this;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int tileID) {
        return manager.paraTileEntity().newTileEntity(world, tileID);
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
    public ArrayList<ItemStack> getDrops(World aWorld, int aX, int aY, int aZ, int aMeta, int aFortune) {
        return tempProxiedBlock().getDrops();
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
}
