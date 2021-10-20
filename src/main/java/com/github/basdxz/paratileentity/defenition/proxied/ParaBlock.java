package com.github.basdxz.paratileentity.defenition.proxied;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ParaBlock extends Block implements IParaItemBlock{
    protected ParaBlock() {
        super(Material.rock);
    }
}
