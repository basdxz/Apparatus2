package com.github.basdxz.apparatus.example;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.ITile;
import com.github.basdxz.apparatus.common.render.IRenderView;
import com.github.basdxz.apparatus.common.resourceold.IRendererOld;

import java.util.Map;

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

    @Override
    public Map<IRenderView, IRendererOld> renderersOld() {
        return null;
    }
}