package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedBlock;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public interface IParaBlock extends IParaManaged {
    Block block();

    default IProxiedBlock proxiedBlock(World world, int posX, int posY, int posZ) {
        return paraTile(world, posX, posY, posZ);
    }

    default IParaTile paraTile(World world, int posX, int posY, int posZ) {
        return manager().paraTile(tileID(world, posX, posY, posZ));
    }

    default int tileID(World world, int posX, int posY, int posZ) {
        return world.getBlockMetadata(posX, posY, posZ);
    }
}
