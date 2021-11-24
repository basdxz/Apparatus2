package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;


// Client-Side
@Mixin(RenderGlobal.class)
public class RenderGlobalMixin {

    @Shadow
    private WorldClient theWorld;

    @Shadow
    private Minecraft mc;

    // Happens when a player lands?
    @Inject(method = "playAuxSFX(Lnet/minecraft/entity/player/EntityPlayer;IIIII)V",
            at = @At(value = "INVOKE_ASSIGN",
                    target = "java/lang/Math.min (FF)F"),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            cancellable = true,
            require = 1)
    private void getBlockMetadataRedirect(EntityPlayer d12, int d4, int d5, int d6, int d7, int d13, CallbackInfo ci, Random random, Block block) {
        if (!(block instanceof IParaBlock))
            return;
        ci.cancel();

        if (mc.gameSettings.particleSetting == 2)
            return;
        if (mc.gameSettings.particleSetting == 1 && theWorld.rand.nextInt(3) == 0)
            return;

        //mc.effectRenderer.addEffect(new EntityBlockDustFXParaTile(
        //        theWorld, posX + 0.5D, posY + 0.5D, posZ + 0.5D,
        //        motionX, motionY, motionZ,
        //        ((IParaBlock) block).paraTile(theWorld, posX, posY, posZ)).applyRenderColor(0));
    }
}
