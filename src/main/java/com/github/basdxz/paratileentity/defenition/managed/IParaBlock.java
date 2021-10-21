package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IProxiedBlock;
import net.minecraft.world.World;

public interface IParaBlock extends IParaManaged {
    default IProxiedBlock getBlock(World world, int posX, int posY, int posZ) {
        return getTile(world, posX, posY, posZ);
    }

    default IParaTile getTile(World world, int posX, int posY, int posZ) {
        return getManager().getTile(getTileID(world, posX, posY, posZ));
    }

    default int getTileID(World world, int posX, int posY, int posZ) {
        return world.getBlockMetadata(posX, posY, posZ);
    }
}
