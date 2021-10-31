package com.github.basdxz.paratileentity.util;

import lombok.experimental.UtilityClass;
import net.minecraft.launchwrapper.Launch;
import ru.timeconqueror.spongemixins.MinecraftURLClassPath;

import java.io.File;
import java.io.FileNotFoundException;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.info;

@UtilityClass
public class Utils {
    public boolean isDevelopmentEnvironment() {
        return (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
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

    public int worldToChunkBlockPos(final int worldBlockPos) {
        return worldBlockPos & 15;
    }
}
