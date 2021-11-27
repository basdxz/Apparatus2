package com.github.basdxz.apparatus.mixins.minecraft;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.shader.TesselatorVertexState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


//TODO: Probably doesn't belong here?
@Mixin(Tessellator.class)
public class TesselatorMixin {

    @Shadow
    private int rawBufferIndex;

    /**
     * author SirFell
     * reason Fixes MinecraftForge#981. Crash on "bad moder rendering"(©LexManos) of transparent/translucent blocks when they draw nothing.
     */
    @Inject(method = "getVertexState", at = @At("HEAD"), cancellable = true)
    public void getVertexStateNatural0Safe(float x, float y, float z, CallbackInfoReturnable<TesselatorVertexState> cir) {
        if (this.rawBufferIndex <= 0) {
            cir.setReturnValue(null);
            cir.cancel();
        }
    }
}
