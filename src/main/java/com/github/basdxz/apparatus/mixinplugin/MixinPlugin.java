package com.github.basdxz.apparatus.mixinplugin;

import com.github.basdxz.apparatus.ApparatusMod;
import com.github.basdxz.apparatus.util.Utils;
import com.google.common.collect.Lists;
import lombok.val;
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
        if (!Utils.isDevelopmentEnvironment()) {
            Utils.loadJar("Chisel");
            Utils.loadJar("journeymap");
        }

        val mixinList = Lists.newArrayList(
                "minecraft.ItemInWorldManagerMixin",
                "minecraft.EffectRendererMixin",
                "minecraft.RenderGlobalMixin",
                "minecraft.RenderItemMixin",
                "minecraft.ItemRendererMixin",
                "minecraft.BlockMixin",
                "minecraft.TesselatorMixin",
                //"minecraft.ChunkMixin",
                "chisel.CTMMixin",
                "chisel.ItemOffsetToolMixin",
                "journeymap.StratumMixin"
        );

        if (!Utils.isDevelopmentEnvironment() && doesOptifineIsExist()) {
            ApparatusMod.warn("Oh no, you included optifine. you absolute monkey.");
            mixinList.add("optifine.WorldRendererMixin");
            mixinList.add("optifine.ShadersMixin");
            mixinList.add("optifine.ItemRendererOFMixin");
            mixinList.add("optifine.RenderBlocksMixin");
        } else {
            mixinList.add("minecraft.WorldRendererMixin");
            mixinList.add("minecraft.RenderBlocksMixin");
        }
        return mixinList;
    }

    public boolean doesOptifineIsExist() {
        try {
            Class.forName("ChunkCacheOF", false, getClass().getClassLoader());
            return true;
        } catch (ClassNotFoundException ignored) {
        }
        return false;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
