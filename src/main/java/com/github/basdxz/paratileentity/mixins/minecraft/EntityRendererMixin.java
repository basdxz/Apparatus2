package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.sortedParticle.StaticStorage;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Inject(method = "orientCamera(F)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/util/MathHelper;sin (F)F",
                    ordinal = 1,
                    by = 2),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    private void renderParticlesInject(float f6, CallbackInfo ci, EntityLivingBase entitylivingbase, float f1, double d0, double d1, double d2, double d7, float f2, float f6_a, double d3, double d4) {
        StaticStorage.camPosX = d0;
        StaticStorage.camPosY = d1;
        StaticStorage.camPosZ = d2;
    }
}
