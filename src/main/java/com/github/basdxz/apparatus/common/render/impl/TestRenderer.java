package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderBufferID;
import com.github.basdxz.apparatus.common.render.IRenderContext;
import com.github.basdxz.apparatus.common.render.IRenderer;
import lombok.*;
import lombok.experimental.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Accessors(fluent = true, chain = true)
public class TestRenderer implements IRenderer {
    public static IRenderer INSTANCE = new TestRenderer();

    @Getter
    protected final List<IRenderBufferID<?>> bufferIDs;
    protected final TestRenderModel.TestRenderModelInstance modelInstance = TestRenderModel.INSTANCE.newModelInstance();

    {
        val bufferIDs = new ArrayList<IRenderBufferID<?>>();
        bufferIDs.add(modelInstance.bufferID());
        this.bufferIDs = Collections.unmodifiableList(bufferIDs);
    }

    @Override
    public void render(@NonNull IRenderContext context) {
        modelInstance.color().set(0F, 1F, 1F, 1F);
        modelInstance.render(context);
    }
}
