package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// Client-Side
@Mixin(EntityDiggingFX.class)
public class EntityDiggingFXMixin {
    private static IParaTile bufferedParaTile;

    /*
        Redirects the getIcon call to the IParaBlock with the correct metas from the buffered tile.

        Since the buffer destroys on read, we buffer the read value.
     */
    @Redirect(method = "<init>(Lnet/minecraft/world/World;DDDDDDLnet/minecraft/block/Block;II)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getIcon " +
                            "(II)Lnet/minecraft/util/IIcon;"),
            require = 1)
    private IIcon getIconRedirect(Block instance, int side, int blockMeta) {
        if (instance instanceof IParaBlock) {
            val manager = ((IParaBlock) instance).manager();
            if (!manager.bufferedTileNull())
                bufferedParaTile = manager.bufferedTile().paraTile();
            if (bufferedParaTile != null)
                return bufferedParaTile.getIcon(ForgeDirection.getOrientation(side));
        }
        return instance.getIcon(side, blockMeta);
    }
}
