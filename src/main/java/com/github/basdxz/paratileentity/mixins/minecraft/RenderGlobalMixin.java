package com.github.basdxz.paratileentity.mixins.minecraft;

import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;

// FIXME: FLAT_FIX (remove?)
// Client-Side
@Mixin(RenderGlobal.class)
public class RenderGlobalMixin {

    ///*
    //    Replaces block meta with IParaTile tileID if block is an instance of IParaBlock.
//
    //    Used to replace the particles of an entity landing on top of a block.
    //*/
    //@Redirect(method = "playAuxSFX(Lnet/minecraft/entity/player/EntityPlayer;IIIII)V",
    //        at = @At(value = "INVOKE",
    //                target = "net/minecraft/client/multiplayer/WorldClient.getBlockMetadata (III)I"),
    //        require = 1)
    //private int getBlockMetadataRedirect(WorldClient instance, int posX, int posY, int posZ) {
    //    return Utils.getBlockMetadataRedirect(instance, posX, posY, posZ);
    //}
}
