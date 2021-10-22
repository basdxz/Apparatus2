package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import lombok.experimental.SuperBuilder;
import lombok.val;

import java.util.Random;

@SuperBuilder
public class ParaTile_two extends ParaTile {
    int tier;

    @Override
    public IParaTile clone() {
        Random rand = new Random();
        int int_random = rand.nextInt(200);
        val para = (ParaTile_two) super.clone();
        para.tier = int_random;
        return para;
    }

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
