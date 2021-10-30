package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class WorldMixin {
    /*
        Injects as close to a TileEntity being slapped down as we physically can.

        When a block is placed, this fires once on the server thread. If a player placed it, then it fires there two.
        Then another time for sync and a fourth time for another sync it seems.

        So four in total then.

        We care about the first two if they are para blocks and don't have loaded tile entities.
     */
    @Inject(method = "setBlock(IIILnet/minecraft/block/Block;II)Z", at = @At("HEAD"), require = 1)
    public void setBlock(int posX, int posY, int posZ, Block block, int meta, int flag, CallbackInfoReturnable<Boolean> cir) {
        if (block instanceof IParaBlock && thiz().getTileEntity(posX, posY, posZ) == null)
            bufferTile((IParaBlock) block, meta);
    }

    /*
        Mostly safe this reference
     */
    private World thiz() {
        return (World) (Object) this;
    }

    /*
        Tosses a reference ParaTile into the managers buffer.
     */
    private static void bufferTile(IParaBlock paraBlock, int tileID) {
        paraBlock.manager().bufferTile(paraBlock.manager().paraTile(tileID));
    }
}
