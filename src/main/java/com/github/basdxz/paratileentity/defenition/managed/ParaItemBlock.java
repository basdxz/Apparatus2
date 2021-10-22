package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Getter
@Accessors(fluent = true)
public class ParaItemBlock extends ItemBlock implements IParaItemBlock {
    protected final IParaTileManager manager;

    public ParaItemBlock(Block block) {
        super(block);
        manager = registerManager();
        init();
    }

    protected IParaTileManager registerManager() {
        if (!(field_150939_a instanceof IParaBlock))
            throw new IllegalArgumentException("Provided Block must implement IParaBlock.");
        return ((IParaBlock) field_150939_a).manager();
    }

    protected void init() {
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + "." + tileID(itemStack);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer player,
                                World world, int posX, int posY, int posZ, int side,
                                float hitX, float hitY, float hitZ, int aMeta) {
        val tileID = tileID(itemStack);

        if (!world.setBlock(posX, posY, posZ, this.field_150939_a, tileID,
                BLOCK_UPDATE_FLAG | SEND_TO_CLIENT_FLAG))
            return false;

        if (world.getBlock(posX, posY, posZ) == this.field_150939_a) {
            this.field_150939_a.onBlockPlacedBy(world, posX, posY, posZ, player, itemStack);
            this.field_150939_a.onPostBlockPlaced(world, posX, posY, posZ, tileID);
        }

        return true;
    }

    @Override
    public int tileID(ItemStack itemStack) {
        return getDamage(itemStack);
    }
}
