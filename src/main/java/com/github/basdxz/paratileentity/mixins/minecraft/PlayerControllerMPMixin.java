package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import net.minecraft.block.Block;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// Client-Side
@Mixin(PlayerControllerMP.class)
public class PlayerControllerMPMixin {

    /*
        Buffers an IParaTile right before block destruction effects are about to be played.

        Currently, only used for breaking particles
     */
    @Inject(method = "onPlayerDestroyBlock(IIII)Z",
            at = @At(value = "INVOKE",
                    target = "net/minecraft/client/multiplayer/WorldClient.playAuxSFX (IIIII)V",
                    shift = At.Shift.BEFORE),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    public void onPlayerDestroyBlockPrePlayAuxSFXAtEntity(int posX, int posY, int posZ, int flags,
                                                          CallbackInfoReturnable<Boolean> cir, ItemStack stack,
                                                          WorldClient world, Block block) {
        if (block instanceof IParaBlock)
            ((IParaBlock) block).manager().bufferedTile(((IParaBlock) block).paraTile(world, posX, posY, posZ));
    }
}
