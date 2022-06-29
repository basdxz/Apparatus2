package com.github.basdxz.apparatus.common.parathing.impl;

import com.github.basdxz.apparatus.common.loader.IParaLoader;
import com.github.basdxz.apparatus.common.loader.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.RegisteredLoader;
import com.github.basdxz.apparatus.common.parathing.IParaBlock;
import com.github.basdxz.apparatus.common.registry.IParaID;
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
public class ParaBlock implements IParaBlock {
    protected final IParaID paraID;
    protected final String localizedName;
    protected final Map<IRendererView, IRenderer> renderers;

    public ParaBlock(@NonNull IParaID paraID, @NonNull String localizedName) {
        this.paraID = paraID;
        this.localizedName = localizedName;

        val renderer = newDefaultBlockRenderer(paraID.registry().domain(), "SwInner");
        renderers = new HashMap<>();
        renderers.put(ENTITY, renderer);
        renderers.put(EQUIPPED, renderer);
        renderers.put(EQUIPPED_FIRST_PERSON, renderer);
        renderers.put(INVENTORY, renderer);
    }

    @NoArgsConstructor
    @RegisteredLoader(registryName = "test_registry")
    public static class Loader implements IParaLoader<ParaBlock> {
        @Override
        public void preInit(@NonNull IPreInitContext<ParaBlock> context) {
            context.register(new ParaBlock(context.newParaID("woag_my_block"), "Woag My Block"));
        }
    }
}
