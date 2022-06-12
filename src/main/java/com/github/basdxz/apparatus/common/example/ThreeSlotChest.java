package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.parathing.IParaTileEntity;
import com.github.basdxz.apparatus.common.resource.IRender;

//Chest with a custom TESR
//Has GUI on client and server side
//Uses NBT to store the internal items`
public class ThreeSlotChest implements IParaTileEntity {
    @Override
    public String paraID() {
        return null;
    }

    @Override
    public String localizedName() {
        return null;
    }

    @Override
    public IRender renderer() {
        return null;
    }
}
