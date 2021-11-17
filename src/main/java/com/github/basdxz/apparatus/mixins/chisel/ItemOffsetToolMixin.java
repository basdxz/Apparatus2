package com.github.basdxz.apparatus.mixins.chisel;

import org.spongepowered.asm.mixin.Mixin;
import team.chisel.item.ItemOffsetTool;

// FIXME: FLAT_FIX
// Client-Side and Server-Side
@Mixin(ItemOffsetTool.class)
public class ItemOffsetToolMixin {

    ///*
    //    Replaces block meta with IParaTile tileID if block is an instance of IParaBlock.

    //    Used to make the canOffset check point at the right id inside the variant manager.
    // */
    //@Redirect(method = "Lteam/chisel/item/ItemOffsetTool;canOffset " +
    //        "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;IIII)Z",
    //        at = @At(value = "INVOKE",
    //                target = "Lnet/minecraft/world/World;getBlockMetadata (III)I"),
    //        require = 1)
    //private int getBlockMetadataRedirect(World instance, int posX, int posY, int posZ) {
    //    return Utils.getBlockMetadataRedirect(instance, posX, posY, posZ);
    //}
}
