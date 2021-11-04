package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(S23PacketBlockChange.class)
public class S23PacketBlockChangeMixin {
    @Shadow
    public Block field_148883_d;

    @Redirect(method = "<init>(IIILnet/minecraft/world/World;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockMetadata (III)I"),
            require = 1)
    private int getBlockMetadataRedirect(World instance, int posX, int posY, int posZ) {
        if (field_148883_d instanceof IParaBlock) {
            val tileEntity = instance.getTileEntity(posX, posY, posZ);
            if (tileEntity instanceof IParaTileEntity)
                return ((IParaTileEntity) tileEntity).tileID();
        }
        return instance.getBlockMetadata(posX, posY, posZ);
    }
}
