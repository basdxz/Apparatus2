package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.proxy.IParaBlockProxy;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public interface IParaBlock extends IParaManaged {
    Block block();

    String getUnlocalizedName();

    default IParaBlockProxy proxiedBlock(IBlockAccess blockAccess, int posX, int posY, int posZ) {
        return paraTile(blockAccess, posX, posY, posZ);
    }

    default IParaBlockProxy proxiedBlock(String tileID) {
        return manager().paraTile(tileID);
    }

    default String tileID(IBlockAccess blockAccess, int posX, int posY, int posZ) {
        return paraTile(blockAccess, posX, posY, posZ).tileID();
    }

    default IParaTile paraTile(IBlockAccess blockAccess, int posX, int posY, int posZ) {
        val tileEntity = blockAccess.getTileEntity(posX, posY, posZ);
        if (tileEntity == null)
            throw new IllegalStateException("TileEntity must not be null.");
        if (!(tileEntity instanceof IParaTileEntity))
            throw new IllegalStateException("Block bound TileEntity must implement IParaTileEntity.");
        return ((IParaTileEntity) tileEntity).paraTile();
    }
}
