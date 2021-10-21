package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ParaTile_two implements IParaTile {
    protected final IParaTileManager manager;

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void updateEntity() {
        System.out.println("HELLOW from ParaTile 2!!");
    }
}
