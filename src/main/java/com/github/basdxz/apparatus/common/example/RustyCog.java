package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resourceold.IRenderer;

import java.util.Map;

//Has a fancy description with a shift key alt description
//NBT storing how many times it's been pressed
public class RustyCog implements IItem {
    @Override
    public IEntityID entityID() {
        return null;
    }

    @Override
    public String localizedName() {
        return null;
    }

    @Override
    public Map<IRendererView, IRenderer> renderers() {
        return null;
    }
}
