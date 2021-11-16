package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import lombok.val;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


// Client-Side
@Mixin(RenderGlobal.class)
public class RenderGlobalMixin {

    /*
        Buffers an IParaTile if block is an instance of IParaBlock.
    */
    @Redirect(method = "playAuxSFX(Lnet/minecraft/entity/player/EntityPlayer;IIIII)V",
            at = @At(value = "INVOKE",
                    target = "net/minecraft/client/multiplayer/WorldClient.getBlockMetadata (III)I"),
            require = 1)
    private int getBlockMetadataRedirect(WorldClient instance, int posX, int posY, int posZ) {
        val block = instance.getBlock(posX, posY, posZ);
        if (block instanceof IParaBlock) {
            ((IParaBlock) block).manager().bufferedTile(((IParaBlock) block).paraTile(instance, posX, posY, posZ));
        }
        return instance.getBlockMetadata(posX, posY, posZ);
    }
}
