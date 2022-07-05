package com.github.basdxz.apparatus.common.entity.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.ITile;
import com.github.basdxz.apparatus.common.loader.EntityLoader;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IPreInitContext;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resource.IRenderer;
import lombok.*;
import lombok.experimental.*;

import java.util.HashMap;
import java.util.Map;

import static com.github.basdxz.apparatus.common.render.impl.RendererView.*;
import static com.github.basdxz.apparatus.common.resource.impl.Renderer.newDefaultBlockRenderer;


@Getter
@Accessors(fluent = true, chain = true)
public class Tile implements ITile {
    protected final IEntityID entityID;
    protected final String localizedName;
    protected final Map<IRendererView, IRenderer> renderers;

    public Tile(@NonNull IEntityID entityID, @NonNull String localizedName) {
        this.entityID = entityID;
        this.localizedName = localizedName;

        val renderer = newDefaultBlockRenderer(entityID.registry().domain(), "SwInner");
        renderers = new HashMap<>();
        renderers.put(ENTITY, renderer);
        renderers.put(EQUIPPED, renderer);
        renderers.put(EQUIPPED_FIRST_PERSON, renderer);
        renderers.put(INVENTORY, renderer);
    }

    @NoArgsConstructor
    @EntityLoader(domainName = "test_registry")
    public static class Loader implements IEntityLoader<Tile> {
        @Override
        public void preInit(@NonNull IPreInitContext<Tile> context) {
            context.register(new Tile(context.newParaID("woag_my_block"), "Woag My Block"));
        }
    }
}
