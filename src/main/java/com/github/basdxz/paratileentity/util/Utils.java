package com.github.basdxz.paratileentity.util;

import lombok.experimental.UtilityClass;
import net.minecraft.launchwrapper.Launch;

@UtilityClass
public class Utils {
    public boolean isDevelopmentEnvironment() {
        return (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    public int worldToChunkBlockPos(int worldBlockPos) {
        return worldBlockPos & 15;
    }
}
