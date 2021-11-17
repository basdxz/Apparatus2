package com.github.basdxz.apparatus.instance;

public interface ITurbinePart {
    String DURABILITY_NBT_TAG = "durability";

    int maxSpeed();

    int maxDurability();

    ITurbinePart durability(int durability);

    int durability();
}
