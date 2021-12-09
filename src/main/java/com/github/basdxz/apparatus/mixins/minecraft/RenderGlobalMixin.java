package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.val;
import lombok.var;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityBlockDustFX;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
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
    private static float TWO_PI_FLOAT = (float) (Math.PI * 2D);

    @Shadow
    private WorldClient theWorld;
    @Shadow
    private Minecraft mc;

    /*
        Replaces the landing particle effect for IParaBlocks.
     */
    @Inject(method = "playAuxSFX(Lnet/minecraft/entity/player/EntityPlayer;IIIII)V",
            at = @At(value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/client/multiplayer/WorldClient;getBlock (III)Lnet/minecraft/block/Block;",
                    shift = At.Shift.AFTER),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            cancellable = true,
            require = 1)
    @SideOnly(Side.CLIENT)
    private void playAuxSFXOnPlayerLand(EntityPlayer entityPlayer, int sfxID, int posX, int posY, int posZ,
                                        int fallHeight, CallbackInfo ci, Random random, Block block) {
        if (mc.gameSettings.particleSetting == 2 || !(block instanceof IParaBlock))
            return;

        if (block.getMaterial() != Material.air) {
            var fallIntensity = Math.min(0.2F + (float) fallHeight / 15.0F, 10.0F);
            if (fallIntensity > 2.5F)
                fallIntensity = 2.5F;
            val particleCount = (int) (150 * fallIntensity);

            val particleX = posX + 0.5F;
            val particleY = posY + 1F;
            val particleZ = posZ + 0.5F;
            val motionY = 0.2D + fallIntensity / 100.0D;

            for (int i = 0; i < particleCount; ++i) {
                if (mc.gameSettings.particleSetting == 1 && theWorld.rand.nextInt(3) == 0)
                    continue;

                val angle = MathHelper.randomFloatClamp(random, 0F, TWO_PI_FLOAT);
                val velocity = MathHelper.randomFloatClamp(random, 0.75F, 1F);
                val motionX = MathHelper.cos(angle) * 0.2F * velocity * velocity * (fallIntensity + 0.2F);
                val motionZ = MathHelper.sin(angle) * 0.2F * velocity * velocity * (fallIntensity + 0.2F);
                val particle = new EntityBlockDustFX(
                        theWorld, particleX, particleY, particleZ,
                        motionX, motionY, motionZ, block, 0);
                particle.setParticleIcon(((IParaBlock) block)
                        .paraTile(theWorld, posX, posY, posZ)
                        .getIcon(ForgeDirection.getOrientation(theWorld.rand.nextInt(6))));
                mc.effectRenderer.addEffect(particle);
            }
        }
        ci.cancel();
    }
}
