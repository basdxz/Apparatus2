package com.github.basdxz.apparatus.mixins.nei;

import codechicken.nei.api.ItemInfo;
import com.github.basdxz.apparatus.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemInfo.class)
public class ItemInfoMixin {
    @Redirect(method = "getIdentifierItems(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;" +
            "Lnet/minecraft/util/MovingObjectPosition;)Ljava/util/ArrayList;",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getPickBlock " +
                            "(Lnet/minecraft/util/MovingObjectPosition;Lnet/minecraft/world/World;" +
                            "IIILnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;"),
            require = 1,
            remap = false)
    private static ItemStack getPickBlockRedirect(Block instance, MovingObjectPosition hit,
                                                  World world, int posX, int posY, int posZ, EntityPlayer player) {
        return Utils.bufferParaTile(instance.getPickBlock(hit, world, posX, posY, posZ, player));
    }
}
