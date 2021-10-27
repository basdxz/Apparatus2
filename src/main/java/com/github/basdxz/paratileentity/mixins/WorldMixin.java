package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = World.class)
public class WorldMixin {

    /*
        Injects as close to a TileEntity being slapped down as we physically can.
     */
    @Inject(method = "setBlock(IIILnet/minecraft/block/Block;II)Z", at = @At("HEAD"), require = 1)
    public void setBlock(int posX, int posY, int posZ, Block block, int meta, int flag, CallbackInfoReturnable<Boolean> cir) {
        if (block instanceof IParaBlock)
            bufferTile((IParaBlock) block, meta);
    }

    /*
        Tosses a reference ParaTile into the managers buffer.
     */
    private static void bufferTile(IParaBlock paraBlock, int tileID) {
        paraBlock.manager().bufferTile(paraBlock.manager().paraTile(tileID));
    }
}
