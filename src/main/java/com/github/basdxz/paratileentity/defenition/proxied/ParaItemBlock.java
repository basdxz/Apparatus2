package com.github.basdxz.paratileentity.defenition.proxied;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

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
        return ((IParaBlock)field_150939_a).getManager();
    }

    protected void init() {
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return manager.getName();
    }
}
