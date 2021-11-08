package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.util.Utils;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// Server-Side
@Mixin(S23PacketBlockChange.class)
public class S23PacketBlockChangeMixin {

    /*
        Changes the created block change packet to include the tileID as metadata for IParaBlock,
        since when the blocks are going to be set in world, that meta will be translated back into a tileID.
     */
    @Redirect(method = "<init>(IIILnet/minecraft/world/World;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockMetadata (III)I"),
            require = 1)
    private int getBlockMetadataRedirect(World instance, int posX, int posY, int posZ) {
        return Utils.getBlockMetadataRedirect(instance, posX, posY, posZ);
    }
}
