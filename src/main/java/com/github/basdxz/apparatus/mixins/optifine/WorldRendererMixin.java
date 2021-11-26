package com.github.basdxz.apparatus.mixins.optifine;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import com.github.basdxz.apparatus.woag.ChunkCacheOF;
import com.github.basdxz.apparatus.woag.ReflectorMethod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.HashSet;

// Client-Side
@SuppressWarnings({"MixinAnnotationTarget", "UnresolvedMixinReference"}) // Hooks into class not present in dev.
@Mixin(targets = "net.minecraft.client.renderer.WorldRenderer", remap = false)
public class WorldRendererMixin {
    private static IParaTile cachedParaTile;

    @Inject(method = "func_147892_a(Lnet/minecraft/entity/EntityLivingBase;)V",
            at = @At(value = "INVOKE",
                    target = "LChunkCacheOF;func_72805_g (III)I"),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    @SideOnly(Side.CLIENT)
    private void getParaTile(EntityLivingBase p_147892_1_, CallbackInfo ci, int xMin, int yMin, int zMain, int xMax,
                             int yMax, int zMax, HashSet var26, Minecraft var9, EntityLivingBase var10, int viewEntityPosX,
                             int viewEntityPosY, int viewEntityPosZ, byte var14, ChunkCacheOF chunkcache,
                             RenderBlocks renderblocks, boolean hasForge, int renderPass, boolean renderNextPass,
                             boolean hasRenderedBlocks, boolean hasGlList, int posY, int posZ, int posX, Block block,
                             boolean hasTileEntity) {
        if (!(block instanceof IParaBlock))
            return;
        cachedParaTile = ((IParaBlock) block).paraTile(chunkcache, posX, posY, posZ);
    }

    @Redirect(method = "func_147892_a(Lnet/minecraft/entity/EntityLivingBase;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;func_149701_w ()I"),
            require = 1)
    @SideOnly(Side.CLIENT)
    private int getRenderBlockPassRedirect(Block instance) {
        if (!(instance instanceof IParaBlock) || cachedParaTile == null)
            return instance.getRenderBlockPass();
        return cachedParaTile.proxyBlock().getRenderBlockPass();
    }

    @Redirect(method = "func_147892_a(Lnet/minecraft/entity/EntityLivingBase;)V",
            at = @At(value = "INVOKE",
                    target = "LReflector;callBoolean (Ljava/lang/Object;LReflectorMethod;[Ljava/lang/Object;)Z",
                    ordinal = 1),
            require = 1)
    @SideOnly(Side.CLIENT)
    private boolean getRenderBlockCanRenderRedirect(Object blockObj, ReflectorMethod method, Object[] oa) {
        if (!(blockObj instanceof IParaBlock) || cachedParaTile == null)
            return ((Block) blockObj).canRenderInPass((int) oa[0]);
        val shouldRender = cachedParaTile.proxyBlock().canRenderInPass((int) oa[0]);
        cachedParaTile = null;
        return shouldRender;
    }
}
