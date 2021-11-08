package com.github.basdxz.paratileentity.mixins.chisel;

import com.github.basdxz.paratileentity.util.Utils;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import team.chisel.item.ItemOffsetTool;

// Client-Side and Server-Side
@Mixin(ItemOffsetTool.class)
public class ItemOffsetToolMixin {

    /*
        Replaces block meta with IParaTile tileID if block is an instance of IParaBlock.
     */
    @Redirect(method = "Lteam/chisel/item/ItemOffsetTool;canOffset " +
            "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;IIII)Z",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockMetadata (III)I"),
            require = 1)
    private int getBlockMetadataRedirect(World instance, int posX, int posY, int posZ) {
        return Utils.getBlockMetadataRedirect(instance, posX, posY, posZ);
    }
}
