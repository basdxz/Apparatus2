package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ParaTile_one extends ParaTile {
    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        System.out.println("HELLOW from ParaTile!!");
    }
}
