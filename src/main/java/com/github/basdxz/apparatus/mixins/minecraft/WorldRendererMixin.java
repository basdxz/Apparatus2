package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.ChunkCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.HashSet;

//TODO add getRenderBlockCanRenderRedirect
@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    private static IParaTile cachedParaTile;

    @Inject(method = "updateRenderer(Lnet/minecraft/entity/EntityLivingBase;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/ChunkCache;getBlockMetadata (III)I"),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    private void updateRendererNextBlock(EntityLivingBase entityLivingBase, CallbackInfo ci, int i, int j, int k, int l, int i1,
                                         int j1, HashSet hashset, Minecraft minecraft, EntityLivingBase entitylivingbase1,
                                         int l1, int i2, int j2, byte b0, ChunkCache chunkcache, RenderBlocks renderblocks,
                                         int k2, boolean flag, boolean flag1, boolean flag2, int posY, int posZ, int posX, Block block) {
        if (!(block instanceof IParaBlock))
            return;

        cachedParaTile = ((IParaBlock) block).paraTile(chunkcache, posX, posY, posZ);
    }

    @Redirect(method = "updateRenderer(Lnet/minecraft/entity/EntityLivingBase;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getRenderBlockPass ()I"),
            require = 1)
    private int getRenderBlockPassRedirect(Block instance) {
        if (!(instance instanceof IParaBlock) || cachedParaTile == null)
            return instance.getRenderBlockPass();
        val pass = cachedParaTile.proxyBlock().getRenderBlockPass();
        cachedParaTile = null;
        return pass;
    }
}
