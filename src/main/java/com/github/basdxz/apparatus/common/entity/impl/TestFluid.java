package com.github.basdxz.apparatus.common.entity.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IFluid;
import com.github.basdxz.apparatus.common.loader.Loader;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.impl.EntityLoader;
import com.github.basdxz.apparatus.common.render.IRendererView;
import com.github.basdxz.apparatus.common.resourceold.IRenderer;
import lombok.*;
import lombok.experimental.*;

import java.util.HashMap;
import java.util.Map;

import static com.github.basdxz.apparatus.common.render.impl.RendererView.*;
import static com.github.basdxz.apparatus.common.resourceold.impl.Renderer.newDefaultBlockRenderer;


@Getter
@Accessors(fluent = true, chain = true)
public class TestFluid implements IFluid {
    protected final IEntityID entityID;
    protected final String localizedName;
    protected final Map<IRendererView, IRenderer> renderers;

    public TestFluid(@NonNull IEntityID entityID, @NonNull String localizedName) {
        this.entityID = entityID;
        this.localizedName = localizedName;

        //TODO: Fluid renderer
        val renderer = newDefaultBlockRenderer(entityID.domain(), "SwInner");
        renderers = new HashMap<>();
        renderers.put(ENTITY, renderer);
        renderers.put(EQUIPPED, renderer);
        renderers.put(EQUIPPED_FIRST_PERSON, renderer);
        renderers.put(INVENTORY, renderer);
    }

    @NoArgsConstructor
    @Loader(domainName = "apparatus")
    public static class TileLoader extends EntityLoader<TestFluid> {
        @Override
        public void preInit(@NonNull IPreInitContext<TestFluid> context) {
            context.register(new TestFluid(context.entityID("woag_my_fluid"), "Woag My Fluid??!?!"));
        }
    }
}
