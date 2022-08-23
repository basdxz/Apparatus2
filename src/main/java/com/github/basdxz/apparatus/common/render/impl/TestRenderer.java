package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.domain.IResourceContainer;
import com.github.basdxz.apparatus.common.render.IRenderBufferID;
import com.github.basdxz.apparatus.common.render.IRenderContext;
import com.github.basdxz.apparatus.common.render.IRenderer;
import com.github.basdxz.apparatus.common.resource.ITextureResource;
import lombok.*;
import lombok.experimental.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.github.basdxz.apparatus.common.resource.impl.ResourceTypes.TEXTURE;
import static com.github.basdxz.apparatus.example.Externals.MINECRAFT_DOMAIN;

@Accessors(fluent = true, chain = true)
public class TestRenderer implements IRenderer {
    public static IRenderer INSTANCE = new TestRenderer();
    protected final Random random = new Random();

    protected final IResourceContainer<ITextureResource> textureContainer =
            MINECRAFT_DOMAIN.resourceContainer("textures/items/apple", TEXTURE);

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
        float exactSecond = (float) ((System.nanoTime() % 1E9) / 1E9F);
        val colArr = Color.getHSBColor(exactSecond, 1F, 1F).getComponents(new float[4]);

        modelInstance.color().set(colArr[0], colArr[1], colArr[2], 1F);
        modelInstance.render(context);
    }
}
