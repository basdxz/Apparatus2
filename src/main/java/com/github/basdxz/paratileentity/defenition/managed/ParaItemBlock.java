package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

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
        return proxiedItemBlock(itemStack).getUnlocalizedName(itemStack);
    }

    // F3 + H Enables Showing Advanced
    @SuppressWarnings("unchecked")  // Unavoidable due to Minecraft providing a raw list.
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List toolTip, boolean advanced) {
        proxiedItemBlock(itemStack).addInformation(itemStack, entityPlayer, toolTip, advanced);
    }

    @Override
    public boolean placeBlockAt(ItemStack itemStack, EntityPlayer entityPlayer,
                                World world, int posX, int posY, int posZ, int side,
                                float hitX, float hitY, float hitZ, int invalidMetaData) {
        return proxiedItemBlock(itemStack).placeBlockAt(itemStack, entityPlayer, world, posX, posY, posZ, side, hitX, hitY, hitZ);
    }
}
