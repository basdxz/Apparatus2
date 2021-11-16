package com.github.basdxz.paratileentity.mixins.minecraft;

import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;

// FIXME: FLAT_FIX (remove?)
// Client-Side and Server-Side
@Mixin(Chunk.class)
public class ChunkMixin {
    //private Block cachedBlock;
//
    ///*
    //    Caches the block before it is set in chunk, needed since @Redirect can't capture locals.
    //*/
    //@Inject(method = "func_150807_a(IIILnet/minecraft/block/Block;I)Z",
    //        at = @At("HEAD"),
    //        require = 1)
    //private void setBlock(int posX, int posY, int posZ, Block block, int blockMeta,
    //                      CallbackInfoReturnable<Boolean> cir) {
    //    cachedBlock = block;
    //}
//
    ///*
    //    Redirects the meta getting of IParaBlock to point at the tileID if the TileEntity is already loaded.
//
    //    Since the meta of IParaBlock will always be zero, this allows the setBlock to skip setting block if it is
    //    the same kind of ParaTile by allowing setBlock to compare the tileID instead.
    //*/
    //@Redirect(method = "func_150807_a(IIILnet/minecraft/block/Block;I)Z",
    //        at = @At(value = "INVOKE",
    //                target = "Lnet/minecraft/world/chunk/Chunk;getBlockMetadata (III)I"),
    //        require = 1)
    //private int getBlockMetadataRedirect(Chunk instance, int posX, int posY, int posZ) {
    //    if (cachedBlock instanceof IParaBlock) {
    //        val tileEntity = instance.getTileEntityUnsafe(posX, posY, posZ);
    //        if (tileEntity instanceof IParaTileEntity)
    //            return ((IParaTileEntity) tileEntity).tileID();
    //    }
    //    return instance.getBlockMetadata(posX, posY, posZ);
    //}
//
    ///*
    //    Redirects the meta setting of blocks if they are IParaBlocks to be 0.
//
    //    This is because I want to keep all IParaBlocks at meta 0 when in world to ease debugging.
    // */
    //@Redirect(method = "func_150807_a(IIILnet/minecraft/block/Block;I)Z",
    //        at = @At(value = "INVOKE",
    //                target = "Lnet/minecraft/world/chunk/storage/ExtendedBlockStorage;setExtBlockMetadata (IIII)V"),
    //        require = 1)
    //private void setExtBlockMetadataRedirect(ExtendedBlockStorage instance, int posX, int posY, int posZ, int blockMeta) {
    //    instance.setExtBlockMetadata(posX, posY, posZ, (cachedBlock instanceof IParaBlock) ? 0 : blockMeta);
    //}
}
