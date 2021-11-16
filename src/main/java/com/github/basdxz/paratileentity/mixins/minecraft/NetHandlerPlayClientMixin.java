package com.github.basdxz.paratileentity.mixins.minecraft;

import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;

// FIXME: FLAT_FIX (remove?)
// Client-Side
@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {
    //@Shadow
    //private Minecraft gameController;
//
    ///*
    //    Buffer the IParaTile when we get a TileEntity update packet from the server relating to an IParaTileEntity.
    // */
    //@Inject(method = "handleUpdateTileEntity(Lnet/minecraft/network/play/server/S35PacketUpdateTileEntity;)V",
    //        at = @At(value = "INVOKE",
    //                target = "Lnet/minecraft/client/multiplayer/WorldClient;getTileEntity " +
    //                        "(III)Lnet/minecraft/tileentity/TileEntity;",
    //                shift = At.Shift.BEFORE),
    //        require = 1)
    //private void handleUpdateTileEntity(S35PacketUpdateTileEntity tileEntityUpdatePacket, CallbackInfo ci) {
    //    if (isNBTFromParaTileEntity(tileEntityUpdatePacket.func_148857_g()))
    //        fixTileEntity(gameController.theWorld,
    //                tileEntityUpdatePacket.func_148856_c(),
    //                tileEntityUpdatePacket.func_148855_d(),
    //                tileEntityUpdatePacket.func_148854_e(),
    //                tileEntityUpdatePacket.func_148857_g());
    //}
//
    ///*
    //    This works!
//
    //    Stores an IParaTile in the buffer
    //*/
    //private static void fixTileEntity(World world, int posX, int posY, int posZ, NBTTagCompound nbtFocus) {
    //    val block = world.getBlock(posX, posY, posZ);
    //    if (!(block instanceof IParaBlock))
    //        return;
//
    //    val tileEntity = Utils.getTileEntityIfExists(world, posX, posY, posZ);
    //    val tileID = nbtFocus.getInteger(TILE_ID_INT_NBT_TAG);
//
    //    if (!tileEntity.isPresent()) {
    //        ((IParaBlock) block).manager().bufferedTile(world, posX, posY, posZ, tileID);
    //        world.getTileEntity(posX, posY, posZ);
    //    } else if (tileEntity.get() instanceof IParaTileEntity) {
    //        if (tileID != ((IParaTileEntity) tileEntity.get()).tileID()) {
    //            // Multiblock update packet
    //            world.removeTileEntity(posX, posY, posZ);
    //            ((IParaBlock) block).manager().bufferedTile(world, posX, posY, posZ, tileID);
    //            world.getTileEntity(posX, posY, posZ);
    //        }
    //    }
    //}
}
