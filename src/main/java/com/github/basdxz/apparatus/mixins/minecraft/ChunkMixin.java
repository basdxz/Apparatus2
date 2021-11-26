package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import net.minecraft.block.Block;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/*
    Whole thing is here to allow block replacement etc, currently kinda buggy (?)
 */
@Mixin(Chunk.class)
public class ChunkMixin {
    private static Block cachedBlock;

    @Redirect(method = "func_150807_a(IIILnet/minecraft/block/Block;I)Z",
            at = @At(value = "INVOKE",
                    target = "net/minecraft/world/chunk/Chunk.getBlock (III)Lnet/minecraft/block/Block;",
                    ordinal = 0),
            require = 1)
    private Block getBlockRedirectCache(Chunk instance, int posX, int posY, int posZ) {
        cachedBlock = instance.getBlock(posX, posY, posZ);
        return cachedBlock;
    }

    @Redirect(method = "func_150807_a(IIILnet/minecraft/block/Block;I)Z",
            at = @At(value = "INVOKE",
                    target = "net/minecraft/world/chunk/Chunk.getBlockMetadata (III)I",
                    ordinal = 0),
            require = 1)
    private int getBlockMetaDataRedirect(Chunk instance, int posX, int posY, int posZ) {
        if (!(cachedBlock instanceof IParaBlock))
            return instance.getBlockMetadata(posX, posY, posZ);
        return 1;
    }
}
