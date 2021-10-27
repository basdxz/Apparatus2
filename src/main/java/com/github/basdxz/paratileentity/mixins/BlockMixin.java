package com.github.basdxz.paratileentity.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = Block.class, remap = true)
public class BlockMixin {
    @Inject(method = "getMaterial",
            at = @At("HEAD"),
            remap = true,
            require = 1,
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void getMaterial(CallbackInfoReturnable<Material> cir) {
        System.out.println("wogi-glogi");
    }
}
