package com.github.basdxz.paratileentity.mixins.minecraft;

import net.minecraft.network.play.server.S23PacketBlockChange;
import org.spongepowered.asm.mixin.Mixin;

// FIXME: FLAT_FIX (remove?)
// Server-Side
@Mixin(S23PacketBlockChange.class)
public class S23PacketBlockChangeMixin {

    ///*
    //    Changes the created block change packet to include the tileID as metadata for IParaBlock,
    //    since when the blocks are going to be set in world, that meta will be translated back into a tileID.
    // */
    //@Redirect(method = "<init>(IIILnet/minecraft/world/World;)V",
    //        at = @At(value = "INVOKE",
    //                target = "Lnet/minecraft/world/World;getBlockMetadata (III)I"),
    //        require = 1)
    //private int getBlockMetadataRedirect(World instance, int posX, int posY, int posZ) {
    //    return Utils.getBlockMetadataRedirect(instance, posX, posY, posZ);
    //}
}
