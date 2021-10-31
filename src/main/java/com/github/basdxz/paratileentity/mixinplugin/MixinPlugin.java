package com.github.basdxz.paratileentity.mixinplugin;

import com.github.basdxz.paratileentity.util.Utils;
import com.google.common.collect.Lists;
import org.spongepowered.asm.lib.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import ru.timeconqueror.spongemixins.MinecraftURLClassPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.info;

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
            info("AND THE RETURN VALUE IS: " + loadJar("Chisel"));

        return Lists.newArrayList(
                "WorldMixin",
                "AnvilChunkLoaderMixin",
                "ItemInWorldManagerMixin",
                "ChunkMixin",
                "NetHandlerPlayClientMixin",
                "CTMMixin"
        );
    }

    public static boolean loadJar(final String jarName) {
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


    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
