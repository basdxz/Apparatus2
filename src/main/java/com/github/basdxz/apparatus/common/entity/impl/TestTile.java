package com.github.basdxz.apparatus.common.entity.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.ITile;
import com.github.basdxz.apparatus.common.loader.Loader;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.impl.EntityLoader;
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
public class TestTile implements ITile {
    protected final IEntityID entityID;
    protected final String localizedName;
    protected final Map<IRendererView, IRenderer> renderers;

    public TestTile(@NonNull IEntityID entityID, @NonNull String localizedName) {
        this.entityID = entityID;
        this.localizedName = localizedName;

        val renderer = newDefaultBlockRenderer(entityID.domain(), "SwInner");
        renderers = new HashMap<>();
        renderers.put(ENTITY, renderer);
        renderers.put(EQUIPPED, renderer);
        renderers.put(EQUIPPED_FIRST_PERSON, renderer);
        renderers.put(INVENTORY, renderer);
    }

    @NoArgsConstructor
    @Loader(domainName = "apparatus")
    public static class TileLoader extends EntityLoader<TestTile> {
        @Override
        public void preInit(@NonNull IPreInitContext<TestTile> context) {
            context.register(new TestTile(context.entityID("woag_my_block"), "Woag My Block"));
        }
    }
}
