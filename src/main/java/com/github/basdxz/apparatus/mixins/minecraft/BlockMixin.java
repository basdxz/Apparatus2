package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Block.class)
public class BlockMixin {
    private static IParaTile cachedParaTile;

    @Redirect(method = "shouldSideBeRendered(Lnet/minecraft/world/IBlockAccess;IIII)Z",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/IBlockAccess;getBlock (III)Lnet/minecraft/block/Block;"),
            require = 1)
    @SideOnly(Side.CLIENT)
    private Block shouldSideBeRenderedGetBlockRedirect(IBlockAccess instance, int posX, int posY, int posZ) {
        val block = Minecraft.getMinecraft().theWorld.getBlock(posX, posY, posZ);
        if (block instanceof IParaBlock)
            cachedParaTile = ((IParaBlock) block).paraTile(Minecraft.getMinecraft().theWorld, posX, posY, posZ);
        return block;
    }

    @SideOnly(Side.CLIENT)
    @Redirect(method = "shouldSideBeRendered(Lnet/minecraft/world/IBlockAccess;IIII)Z",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;isOpaqueCube ()Z"),
            require = 1)
    private boolean shouldSideBeRenderedIsOpaqueCubeRedirect(Block instance) {
        if (!(instance instanceof IParaBlock) || cachedParaTile == null)
            return instance.isOpaqueCube();
        val isOpaqueCube = cachedParaTile.proxyBlock().isOpaqueCube();
        cachedParaTile = null;
        return isOpaqueCube;
    }
}
