package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedTileEntity;
import lombok.Getter;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ParaItemBlock extends ItemBlock implements IParaItemBlock {
    @Getter
    protected final IParaTileManager manager;

    public ParaItemBlock(Block block) {
        super(block);
        manager = registerManager();
        init();
    }

    protected IParaTileManager registerManager() {
        if (!(field_150939_a instanceof IParaBlock))
            throw new IllegalArgumentException("Provided Block must implement IParaBlock.");
        return ((IParaBlock) field_150939_a).getManager();
    }

    protected void init() {
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return manager.getName();
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player,
                                World world, int posX, int posY, int posZ, int side,
                                float hitX, float hitY, float hitZ, int aMeta) {
        val tileID = getTileID(itemStack);

        if (!world.setBlock(posX, posY, posZ, this.field_150939_a, tileID,
                BLOCK_UPDATE_FLAG | SEND_TO_CLIENT_FLAG)) {
            return false;
        }

        val tileEntity = world.getTileEntity(posX, posY, posZ);;
        if (!(tileEntity instanceof IParaTileEntity))
            throw new IllegalStateException("Block bound TileEntity must implement IParaTileEntity.");
        ((IParaTileEntity)tileEntity).setTileID(tileID);

        if (world.getBlock(posX, posY, posZ) == this.field_150939_a) {
            this.field_150939_a.onBlockPlacedBy(world, posX, posY, posZ, player, itemStack);
            this.field_150939_a.onPostBlockPlaced(world, posX, posY, posZ, tileID);
        }

        return true;
    }

    @Override
    public int getTileID(ItemStack itemStack) {
        return getDamage(itemStack);
    }
}
