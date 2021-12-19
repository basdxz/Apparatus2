package com.github.basdxz.apparatus.mixinplugin;

import com.google.common.collect.Lists;
import lombok.val;
import net.minecraft.launchwrapper.Launch;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import ru.timeconqueror.spongemixins.MinecraftURLClassPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import static com.github.basdxz.apparatus.util.LoggingUtil.info;
import static com.github.basdxz.apparatus.util.LoggingUtil.warn;

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
        if (runningForRealsies()) {
            loadJar("Chisel-1.7.10");
            loadJar("journeymap");
            loadJar("NotEnoughItems");
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
                "journeymap.StratumMixin",
                "nei.ItemInfoMixin",
                "nei.NEIServerUtilsMixin"
        );

        if (runningForRealsies() && doesOptifineIsExist()) {
            warn("Oh no, you included optifine. you absolute monkey.");
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

    public boolean runningForRealsies() {
        return !((boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment"));
    }

    public boolean loadJar(final String jarName) {
        try {
            File jar = MinecraftURLClassPath.getJarInModPath(jarName);
            if (jar == null) {
                info("Jar not found: " + jarName);
                return false;
            }

            info("Attempting to add " + jar + " to the URL Class Path");
            if (!jar.exists())
                throw new FileNotFoundException(jar.toString());
            MinecraftURLClassPath.addJar(jar);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
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
