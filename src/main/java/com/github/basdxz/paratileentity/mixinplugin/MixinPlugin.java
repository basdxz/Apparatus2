package com.github.basdxz.paratileentity.mixinplugin;

import com.github.basdxz.paratileentity.util.Utils;
import com.google.common.collect.Lists;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

//TODO Clean up mixin configs and packages
public class MixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        if (!Utils.isDevelopmentEnvironment())
            Utils.loadJar("Chisel");

        return Lists.newArrayList(
                "minecraft.WorldMixin",
                "minecraft.AnvilChunkLoaderMixin",
                "minecraft.ItemInWorldManagerMixin",
                "minecraft.ChunkMixin",
                "minecraft.NetHandlerPlayClientMixin",
                "minecraft.S23PacketBlockChangeMixin",
                "minecraft.EffectRendererMixin",
                "minecraft.EntityDiggingFXMixin",
                "minecraft.PlayerControllerMPMixin",
                "chisel.CTMMixin",
                "chisel.ItemOffsetToolMixin"
        );
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
