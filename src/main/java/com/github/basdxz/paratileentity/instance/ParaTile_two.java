package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ParaTile_two extends ParaTile {
    int tier;

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        System.out.println("HELLOW from ParaTile 2!!");
        System.out.println("tier: " + tier);
    }
}
