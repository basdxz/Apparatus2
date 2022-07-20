package com.github.basdxz.apparatus.example;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.ITile;

//Chest with a custom TESR
//Has GUI on client and server side
//Uses NBT to store the internal items`
public class ThreeSlotChest implements ITile {
    @Override
    public IEntityID entityID() {
        return null;
    }

    @Override
    public String localizedName() {
        return null;
    }
}
