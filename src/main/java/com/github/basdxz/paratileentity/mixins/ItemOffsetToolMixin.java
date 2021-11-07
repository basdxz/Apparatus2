package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import lombok.val;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import team.chisel.item.ItemOffsetTool;

@Mixin(ItemOffsetTool.class)
public class ItemOffsetToolMixin {

    // todo de-duplicate
    @Redirect(method = "Lteam/chisel/item/ItemOffsetTool;canOffset " +
            "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;IIII)Z",
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
