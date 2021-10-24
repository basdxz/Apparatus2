package com.github.basdxz.paratileentity.instance;

public interface ITurbinePart {
    String DURABILITY_NBT_TAG = "durability";

    int maxSpeed();

    int maxDurability();

    void durability(int durability);

    int durability();
}
