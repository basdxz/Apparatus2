package com.github.basdxz.apparatus.common.parathing.impl;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;
import lombok.*;
import lombok.experimental.*;

import java.util.HashMap;
import java.util.Map;

import static com.github.basdxz.apparatus.common.render.impl.RendererView.*;
import static com.github.basdxz.apparatus.common.resource.impl.Renderer.newDefaultSpriteRenderer;


@Getter
@Accessors(fluent = true, chain = true)
public class ParaItem implements IParaItem {
    protected final String paraID;
    protected final String localizedName;
    protected final Map<IRendererView, IRenderer> renderers;

    {
        val renderer = newDefaultSpriteRenderer("apparatus", "SwInner");
        renderers = new HashMap<>();
        renderers.put(ENTITY, renderer);
        renderers.put(EQUIPPED, renderer);
        renderers.put(EQUIPPED_FIRST_PERSON, renderer);
        renderers.put(INVENTORY, renderer);
    }

    public ParaItem(@NonNull String paraID, @NonNull String localizedName) {
        this.paraID = paraID;
        this.localizedName = localizedName;
    }
}
