package com.github.basdxz.paratileentity.mixins.minecraft;

import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import org.spongepowered.asm.mixin.Mixin;

// FIXME: FLAT_FIX (remove?)
// Server-Side
@Mixin(AnvilChunkLoader.class)
public class AnvilChunkLoaderMixin {

    /*
        Used to buffer an IParaTile before it is initialised in world as it is being loaded from NBT.
     */
    //@Inject(method = "loadEntities",
    //        at = @At(value = "INVOKE",
    //                target = "Lnet/minecraft/tileentity/TileEntity;createAndLoadEntity " +
    //                        "(Lnet/minecraft/nbt/NBTTagCompound;)Lnet/minecraft/tileentity/TileEntity;",
    //                shift = At.Shift.BEFORE),
    //        locals = LocalCapture.CAPTURE_FAILEXCEPTION,
    //        require = 1)
    //public void loadEntities(World world, NBTTagCompound nbt, Chunk chunk, CallbackInfo ci,
    //                         NBTTagList nbt1, NBTTagList nbt2, int i, NBTTagCompound nbtFocus) {
    //    if (isNBTFromParaTileEntity(nbtFocus))
    //        bufferTile(world, chunk, nbtFocus);
    //}

    /*
        Buffers a given IParaTile instance from given NBT.
     */
    //private static void bufferTile(World world, Chunk chunk, NBTTagCompound nbtTagCompound) {
    //    val posX = nbtTagCompound.getInteger(TILE_ENTITY_X_POS_INT_NBT_TAG);
    //    val posY = nbtTagCompound.getInteger(TILE_ENTITY_Y_POS_INT_NBT_TAG);
    //    val posZ = nbtTagCompound.getInteger(TILE_ENTITY_Z_POS_INT_NBT_TAG);
    //    val tileID = nbtTagCompound.getInteger(TILE_ID_INT_NBT_TAG);
//
    //    val block = chunk.getBlock(
    //            Utils.worldToChunkBlockPosXZ(posX),
    //            posY,
    //            Utils.worldToChunkBlockPosXZ(posZ));
    //    if (!(block instanceof IParaBlock))
    //        return;
//
    //    val paraBlock = (IParaBlock) block;
    //    paraBlock.manager().bufferedTile(world, posX, posY, posZ, tileID);
    //}
}
