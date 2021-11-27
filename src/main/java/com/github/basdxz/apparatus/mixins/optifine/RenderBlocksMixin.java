package com.github.basdxz.apparatus.mixins.optifine;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@SuppressWarnings({"MixinAnnotationTarget", "UnresolvedMixinReference"})
@Mixin(value = RenderBlocks.class, remap = false)
public class RenderBlocksMixin {
    @Shadow
    public IBlockAccess field_147845_a;

    private static int cachedPosX;
    private static int cachedPosY;
    private static int cachedPosZ;

    @Redirect(method = "getAmbientOcclusionLightValue(III)F",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/IBlockAccess;func_147439_a (III)Lnet/minecraft/block/Block;"),
            require = 1)
    private Block getAmbientOcclusionLightValueHead(IBlockAccess instance, int posX, int posY, int posZ) {
        cachedPosX = posX;
        cachedPosY = posY;
        cachedPosZ = posZ;
        return instance.getBlock(posX, posY, posZ);
    }

    @Redirect(method = "getAmbientOcclusionLightValue(III)F",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;func_149637_q ()Z"),
            require = 1)
    private boolean getAmbientOcclusionLightValueRedirect(Block instance) {
        if (!(instance instanceof IParaBlock))
            return instance.isBlockNormalCube();

        return ((IParaBlock) instance)
                .paraTile(field_147845_a, cachedPosX, cachedPosY, cachedPosZ)
                .isBlockNormalCube();
    }
}
