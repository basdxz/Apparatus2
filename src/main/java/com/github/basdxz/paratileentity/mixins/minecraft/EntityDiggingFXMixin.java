package com.github.basdxz.paratileentity.mixins.minecraft;

import net.minecraft.client.particle.EntityDiggingFX;
import org.spongepowered.asm.mixin.Mixin;

// FIXME: FLAT_FIX
// Client-Side
@Mixin(EntityDiggingFX.class)
public class EntityDiggingFXMixin {
    //private static int bufferedTileID = NULL_TILE_ID;
//
    ///*
    //    Redirects the getIcon call to the IParaBlock with the correct metas from the buffered tile.
//
    //    Since the buffer destroys on read, we buffer the read value.
    // */
    //@Redirect(method = "<init>(Lnet/minecraft/world/World;DDDDDDLnet/minecraft/block/Block;II)V",
    //        at = @At(value = "INVOKE",
    //                target = "Lnet/minecraft/block/Block;getIcon " +
    //                        "(II)Lnet/minecraft/util/IIcon;"),
    //        require = 1)
    //private IIcon getIconRedirect(Block instance, int side, int blockMeta) {
    //    if (instance instanceof IParaBlock && blockMeta == NULL_TILE_ID) {
    //        val manager = ((IParaBlock) instance).manager();
    //        if (!manager.bufferedTileNull())
    //            bufferedTileID = manager.bufferedTile().tileID();
    //        return instance.getIcon(side, bufferedTileID);
    //    }
    //    return instance.getIcon(side, blockMeta);
    //}
}
