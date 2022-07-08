package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.common.render.IRenderView;
import com.github.basdxz.apparatus.common.resourceold.IRendererOld;

import java.util.Map;

//Bow that shoots every arrow in the inventory at once
public class BFGTheBow implements IItem {
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
