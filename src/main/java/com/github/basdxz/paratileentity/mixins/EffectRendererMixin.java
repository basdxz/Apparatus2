package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import lombok.val;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EffectRenderer.class)
public class EffectRendererMixin {
    @Redirect(method = "addBlockHitEffects(IIII)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockMetadata (III)I"),
            require = 1)
    private int getBlockMetadataRedirect(World instance, int posX, int posY, int posZ) {
        val block = instance.getBlock(posX, posY, posZ);
        if (block instanceof IParaBlock) {
            val tileEntity = instance.getTileEntity(posX, posY, posZ);
            if (tileEntity instanceof IParaTileEntity)
                return ((IParaTileEntity) tileEntity).tileID();
        }
        return instance.getBlockMetadata(posX, posY, posZ);
    }
}
