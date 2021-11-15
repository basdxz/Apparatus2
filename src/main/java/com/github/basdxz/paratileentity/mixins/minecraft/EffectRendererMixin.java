package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.util.Utils;
import lombok.val;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Client-Side
@Mixin(EffectRenderer.class)
public class EffectRendererMixin {
    @Shadow
    private List[] fxLayers;


    /*
        Replaces block meta with IParaTile tileID if block is an instance of IParaBlock.

        Used to replace the block hit particles when a block is being mined.
     */
    @Redirect(method = "addBlockHitEffects(IIII)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockMetadata (III)I"),
            require = 1)
    private int getBlockMetadataRedirect(World instance, int posX, int posY, int posZ) {
        return Utils.getBlockMetadataRedirect(instance, posX, posY, posZ);
    }

    @SuppressWarnings("unchecked")
    @Inject(method = "renderParticles(Lnet/minecraft/entity/Entity;F)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads ()V"),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    private void renderParticlesInject(Entity p_78874_1_, float p_78874_2_, CallbackInfo ci,
                                       float f1, float f2, float f3, float f4, float f5, int k, int i,
                                       Tessellator tessellator) {
        val x = ActiveRenderInfo.objectX + p_78874_1_.posX;
        val y = ActiveRenderInfo.objectY + p_78874_1_.posY;
        val z = ActiveRenderInfo.objectZ + p_78874_1_.posZ;
        ((ArrayList<EntityFX>) fxLayers[i]).sort(Comparator.comparing(entityFX -> -entityFX.getDistanceSq(x, y, z)));
    }
}
