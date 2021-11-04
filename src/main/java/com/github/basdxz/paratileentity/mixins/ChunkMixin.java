package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Chunk.class)
public class ChunkMixin {
    private Block cachedBlock;

    /*
        Caches the block before it has a chance to be set in a chunk.
    */
    @Inject(method = "func_150807_a(IIILnet/minecraft/block/Block;I)Z",
            at = @At("HEAD"),
            require = 1)
    private void func_150807_a(int posX, int posY, int posZ, Block block, int blockMeta, CallbackInfoReturnable<Boolean> cir) {
        cachedBlock = block;
    }

    /*
        Redirects the meta setting of blocks if they are IParaBlocks to be 0.
    */
    @Redirect(method = "func_150807_a(IIILnet/minecraft/block/Block;I)Z",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/chunk/Chunk;getBlockMetadata (III)I"),
            require = 1)
    private int getBlockMetadataRedirect(Chunk instance, int posX, int posY, int posZ) {
        if (cachedBlock instanceof IParaBlock) {
            val tileEntity = instance.getTileEntityUnsafe(posX, posY, posZ);
            if (tileEntity instanceof IParaTileEntity)
                return ((IParaTileEntity) tileEntity).tileID();
        }
        return instance.getBlockMetadata(posX, posY, posZ);
    }

    /*
        Redirects the meta setting of blocks if they are IParaBlocks to be 0.
     */
    @Redirect(method = "func_150807_a(IIILnet/minecraft/block/Block;I)Z",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/chunk/storage/ExtendedBlockStorage;setExtBlockMetadata (IIII)V"),
            require = 1)
    private void setExtBlockMetadataRedirect(ExtendedBlockStorage instance, int posX, int posY, int posZ, int blockMeta) {
        instance.setExtBlockMetadata(posX, posY, posZ, (cachedBlock instanceof IParaBlock) ? 0 : blockMeta);
    }
}
