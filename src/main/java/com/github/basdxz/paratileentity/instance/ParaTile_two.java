package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ParaTile_two extends ParaTile {
    int tier;

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        System.out.println("HELLOW from ParaTile 2!!");
    }
}
