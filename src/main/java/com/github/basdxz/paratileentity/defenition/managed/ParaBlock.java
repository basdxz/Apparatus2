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
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@Getter
@Accessors(fluent = true)
public class ParaBlock extends BlockContainer implements IParaBlock {
    protected final IParaTileManager manager;

    public ParaBlock(IParaTileManager manager) {
        super(Material.rock);
        this.manager = manager;
        init(manager.itemClass(), manager.name());
    }

    protected void init(Class<? extends ItemBlock> itemClass, String name) {
        setBlockName(MODID + "." + name);
        setBlockTextureName(MODID + ":" + name);
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
        val tile = blockAccess.getTileEntity(posX, posY, posZ);
        if (!(tile instanceof IParaTileEntity))
            throw new RuntimeException("Bound TileEntity must be instance of IParaTileEntity.");
        val paraTile = ((IParaTileEntity) tile).paraTile();

        return paraTile.getIcon(ForgeDirection.getOrientation(side));
    }

    @Override
    public IIcon getIcon(int side, int tileID) {
        return manager.paraTile(tileID).getIcon(ForgeDirection.getOrientation(side));
    }

    @Override
    public int getDamageValue(World world, int posX, int posY, int posZ) {
        val tTileEntity = world.getTileEntity(posX, posY, posZ);
        if (!(tTileEntity instanceof IParaTileEntity))
            throw new IllegalStateException("Block bound TileEntity must implement IParaTileEntity.");
        return ((IParaTileEntity) tTileEntity).tileID();
    }
}
