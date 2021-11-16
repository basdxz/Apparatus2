package com.github.basdxz.paratileentity.defenition.tile;

import net.minecraft.world.World;

public interface IBufferedParaTile {
    World world();

    int posX();

    int posY();

    int posZ();

    default String tileID() {
        return paraTile().tileID();
    }

    IParaTile paraTile();
}
