package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Client-Side
@Mixin(EffectRenderer.class)
public abstract class EffectRendererMixin {
    @SuppressWarnings("rawtypes") // Required since Minecraft provides us a raw list.
    @Shadow
    private List[] fxLayers;

    @Shadow
    public abstract void addEffect(EntityFX p_78873_1_);

    @Shadow
    protected World worldObj;

    /*
        Replaces the destruction particle effect for IParaBlocks.
     */
    @Inject(method = "addBlockDestroyEffects(IIILnet/minecraft/block/Block;I)V",
            at = @At(value = "HEAD"),
            cancellable = true,
            require = 1)
    @SideOnly(Side.CLIENT)
    private void addBlockDestroyEffectsReplacement(int posX, int posY, int posZ, Block block, int side,
                                                   CallbackInfo ci) {
        if (!(block instanceof IParaBlock))
            return;
        // TODO: This should be a proxied function.
        if (!block.addDestroyEffects(worldObj, posX, posY, posZ, side, (EffectRenderer) (Object) this)) {
            val tile = ((IParaBlock) block).paraTile(worldObj, posX, posY, posZ);
            val popSize = 4;
            for (int i = 0; i < popSize; ++i) {
                for (int j = 0; j < popSize; ++j) {
                    for (int k = 0; k < popSize; ++k) {
                        double dPosX = (double) posX + ((double) i + 0.5D) / (double) popSize;
                        double dPosY = (double) posY + ((double) j + 0.5D) / (double) popSize;
                        double dPosZ = (double) posZ + ((double) k + 0.5D) / (double) popSize;
                        val particle = new EntityDiggingFX(
                                worldObj, dPosX, dPosY, dPosZ,
                                dPosX - (double) posX - 0.5D,
                                dPosY - (double) posY - 0.5D,
                                dPosZ - (double) posZ - 0.5D,
                                block, 0);
                        particle.setParticleIcon(
                                tile.getIcon(ForgeDirection.getOrientation(worldObj.rand.nextInt(6))));
                        addEffect(particle);
                    }
                }
            }
        }
        ci.cancel();
    }

    /*
        Replaces the hit particle effect for IParaBlocks.
    */
    @Inject(method = "addBlockHitEffects(IIII)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/particle/EffectRenderer;addEffect" +
                            " (Lnet/minecraft/client/particle/EntityFX;)V"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    @SideOnly(Side.CLIENT)
    private void addBlockHitEffectsReplacement(int posX, int posY, int posZ, int side, CallbackInfo ci,
                                               Block block, float f, double dPosX, double dPosY, double dPosZ) {
        if (!(block instanceof IParaBlock))
            return;
        val particle = new EntityDiggingFX(
                worldObj, dPosX, dPosY, dPosZ,
                dPosX - (double) posX - 0.5D,
                dPosY - (double) posY - 0.5D,
                dPosZ - (double) posZ - 0.5D,
                block, 0);
        particle.setParticleIcon(((IParaBlock) block)
                .paraTile(worldObj, posX, posY, posZ)
                .getIcon(ForgeDirection.getOrientation(side)));
        addEffect(particle);
        ci.cancel();
    }

    // TODO: This is a rendering fix and belongs in some other mod as it's actions are out of project scope.
    @SuppressWarnings("unchecked") // Required since Minecraft provides us a raw list.
    @Inject(method = "renderParticles(Lnet/minecraft/entity/Entity;F)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/Tessellator;startDrawingQuads ()V"),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    @SideOnly(Side.CLIENT)
    private void renderParticlesInject(Entity p_78874_1_, float p_78874_2_, CallbackInfo ci,
                                       float f1, float f2, float f3, float f4, float f5, int k, int i,
                                       Tessellator tessellator) {
        val x = ActiveRenderInfo.objectX + p_78874_1_.posX;
        val y = ActiveRenderInfo.objectY + p_78874_1_.posY;
        val z = ActiveRenderInfo.objectZ + p_78874_1_.posZ;
        ((ArrayList<EntityFX>) fxLayers[i]).sort(Comparator.comparing(entityFX -> -entityFX.getDistanceSq(x, y, z)));
    }
}
