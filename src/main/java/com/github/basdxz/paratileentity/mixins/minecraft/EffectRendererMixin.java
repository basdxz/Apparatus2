package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.util.Utils;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// Client-Side
@Mixin(EffectRenderer.class)
public class EffectRendererMixin {

    /*
        Replaces block meta with IParaTile tileID if block is an instance of IParaBlock.
     */
    @Redirect(method = "addBlockHitEffects(IIII)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockMetadata (III)I"),
            require = 1)
    private int getBlockMetadataRedirect(World instance, int posX, int posY, int posZ) {
        return Utils.getBlockMetadataRedirect(instance, posX, posY, posZ);
    }
}
