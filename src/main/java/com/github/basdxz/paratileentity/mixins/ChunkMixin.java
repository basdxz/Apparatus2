package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
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
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/chunk/storage/ExtendedBlockStorage;setExtBlockMetadata (IIII)V",
                    shift = At.Shift.BEFORE),
            require = 1)
    private void func_150807_a(int posX, int posY, int posZ, Block block, int flag, CallbackInfoReturnable<Boolean> cir) {
        cachedBlock = block;
    }

    /*
        Redirects the meta setting of blocks if they are IParaBlocks to be 0.
     */
    @Redirect(method = "func_150807_a(IIILnet/minecraft/block/Block;I)Z",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/chunk/storage/ExtendedBlockStorage;setExtBlockMetadata (IIII)V"),
            require = 1)
    private void setExtBlockMetadataRedirect(ExtendedBlockStorage instance, int p_76654_1_, int p_76654_2_, int p_76654_3_, int p_76654_4_) {
        instance.setExtBlockMetadata(p_76654_1_, p_76654_2_, p_76654_3_, (cachedBlock instanceof IParaBlock) ? 0 : p_76654_4_);
    }
}
