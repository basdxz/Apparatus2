package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderBlocks.class)
public class RenderBlocksMixin {
    @Shadow
    public IBlockAccess blockAccess;

    private static int cachedPosX;
    private static int cachedPosY;
    private static int cachedPosZ;

    @Redirect(method = {"renderStandardBlockWithAmbientOcclusion(Lnet/minecraft/block/Block;IIIFFF)Z",
            "renderStandardBlockWithAmbientOcclusionPartial(Lnet/minecraft/block/Block;IIIFFF)Z"},
            at = @At(value = "INVOKE",
                    target = "net/minecraft/world/IBlockAccess.getBlock (III)Lnet/minecraft/block/Block;"),
            require = 2) //TODO: Actually need way more than two, same bug on Optifine
    private Block blockRenderWithAOHead(IBlockAccess instance, int posX, int posY, int posZ) {
        cachedPosX = posX;
        cachedPosY = posY;
        cachedPosZ = posZ;
        return instance.getBlock(posX, posY, posZ);
    }

    @Redirect(method = {"renderStandardBlockWithAmbientOcclusion(Lnet/minecraft/block/Block;IIIFFF)Z",
            "renderStandardBlockWithAmbientOcclusionPartial(Lnet/minecraft/block/Block;IIIFFF)Z"},
            at = @At(value = "INVOKE",
                    target = "net/minecraft/block/Block.getAmbientOcclusionLightValue ()F"),
            require = 108)
    private float getAmbientOcclusionLightValueRedirect(Block instance) {
        if (!(instance instanceof IParaBlock))
            return instance.getAmbientOcclusionLightValue();

        return ((IParaBlock) instance)
                .paraTile(blockAccess, cachedPosX, cachedPosY, cachedPosZ)
                .getAmbientOcclusionLightValue();
    }
}
