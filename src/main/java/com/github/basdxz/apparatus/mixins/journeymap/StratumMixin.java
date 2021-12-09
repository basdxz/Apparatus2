package com.github.basdxz.apparatus.mixins.journeymap;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.managed.IParaTileEntity;
import com.github.basdxz.apparatus.util.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import journeymap.client.cartography.Stratum;
import journeymap.client.model.BlockMD;
import journeymap.client.model.ChunkMD;
import lombok.val;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/*
    Only part fixes the issue, blocks still show up as white on the map for the time being.
 */
@Mixin(value = Stratum.class, remap = false)
public class StratumMixin {
    @Redirect(method = "set(Ljourneymap/client/model/ChunkMD;Ljourneymap/client/model/BlockMD;" +
            "IIILjava/lang/Integer;)Ljourneymap/client/cartography/Stratum;",
            at = @At(value = "INVOKE",
                    target = "Ljourneymap/client/model/ChunkMD;getLightOpacity " +
                            "(Ljourneymap/client/model/BlockMD;III)I"),
            require = 1)
    @SideOnly(Side.CLIENT)
    private int getLightOpacityRedirect(ChunkMD instance, BlockMD blockMD, int posX, int posY, int posZ) {
        val block = blockMD.getBlock();
        if (block instanceof IParaBlock) {
            val tileEntity = Utils.getTileEntityIfExists(instance.getChunk(), posX, posY, posZ);
            if (tileEntity.orElse(null) instanceof IParaTileEntity)
                return ((IParaTileEntity)tileEntity.get()).paraTile().getLightOpacity();
        }
        return instance.getLightOpacity(blockMD, posX, posY, posZ);
    }
}
