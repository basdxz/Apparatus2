package com.github.basdxz.apparatus.common.parathing.impl;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.render.impl.RendererView;
import com.github.basdxz.apparatus.common.resource.IModel;
import com.github.basdxz.apparatus.common.resource.IRenderer;
import com.github.basdxz.apparatus.common.resource.impl.*;
import lombok.*;
import lombok.experimental.*;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Getter
@Accessors(fluent = true, chain = true)
public class ParaItem implements IParaItem {
    protected final String paraID;
    protected final String localizedName;
    protected final Map<IRendererView, IRenderer> renderers;

    {
        val props = new ModelProperties(
                new ResourceLocation("apparatus", "prop"),
                true,
                true,
                true,
                Color.WHITE,
                new Vector3f(),
                new Quaternionf(),
                new Vector3f()
        );
        val tex = new TextureResource(new ResourceLocation("apparatus", "SwInner"));
        val models = new ArrayList<IModel>();
        models.add(new SpriteModel(props, 1F, tex));

        val renderer = new Renderer(models);
        renderers = new HashMap<>();
        renderers.put(RendererView.ENTITY, renderer);
        renderers.put(RendererView.EQUIPPED, renderer);
        renderers.put(RendererView.EQUIPPED_FIRST_PERSON, renderer);
        renderers.put(RendererView.INVENTORY, renderer);
    }

    public ParaItem(@NonNull String paraID, @NonNull String localizedName) {
        this.paraID = paraID;
        this.localizedName = localizedName;
    }
}
