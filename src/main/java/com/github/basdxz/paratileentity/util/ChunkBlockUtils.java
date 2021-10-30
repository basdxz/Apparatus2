package com.github.basdxz.paratileentity.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ChunkBlockUtils {
    public int worldToChunkBlockPos(int worldBlockPos) {
        return worldBlockPos & 15;
    }
}
