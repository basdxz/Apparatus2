package com.github.basdxz.apparatus.common.parathing.impl;

import com.github.basdxz.apparatus.common.example.Externals;
import com.github.basdxz.apparatus.common.loader.IInitContext;
import com.github.basdxz.apparatus.common.loader.IParaLoader;
import com.github.basdxz.apparatus.common.loader.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.RegisteredLoader;
import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.common.recipe.impl.Recipe;
import com.github.basdxz.apparatus.common.recipe.impl.RecipeComponent;
import com.github.basdxz.apparatus.common.registry.IParaID;
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
    protected final IParaID paraID;
    protected final String localizedName;
    protected final Map<IRendererView, IRenderer> renderers;

    public ParaItem(@NonNull IParaID paraID, @NonNull String localizedName) {
        this.paraID = paraID;
        this.localizedName = localizedName;

        val renderer = newDefaultSpriteRenderer(paraID.registry().domain(), "SwInner");
        renderers = new HashMap<>();
        renderers.put(ENTITY, renderer);
        renderers.put(EQUIPPED, renderer);
        renderers.put(EQUIPPED_FIRST_PERSON, renderer);
        renderers.put(INVENTORY, renderer);
    }

    @NoArgsConstructor
    @RegisteredLoader(registryName = "test_registry")
    public static class Loader implements IParaLoader<ParaItem> {
        @Override
        public void preInit(@NonNull IPreInitContext<ParaItem> context) {
            context.register(new ParaItem(context.newParaID("woag_my_item"), "Woag My Item"));
            context.register(new ParaItem(context.newParaID("woag_my_item2"), "Woag My Item"));
            context.register(new ParaItem(context.newParaID("woag_my_item3"), "Woag My Item"));
        }

        @Override
        public void init(@NonNull IInitContext<ParaItem> context) {
            //give basdxz minecraft:stick 1
            val stick = RecipeComponent.newRecipeItem(Externals.MINECRAFT_REGISTRY.newParaID("stick"));
            val arrow = RecipeComponent.newRecipeItem(Externals.MINECRAFT_REGISTRY.newParaID("arrow"));
            val feather = RecipeComponent.newRecipeItem(Externals.MINECRAFT_REGISTRY.newParaID("feather"));

            val recipe = Recipe.shapelessBuilder()
                    .in(stick).in(stick).in(stick).in(stick)
                    .out(arrow)
                    .build();
            context.register(recipe);

            val recipe2 = Recipe.shapedBuilder()
                    .in(null).in(feather).in(null)
                    .in(feather).in(feather).in(null)
                    .in(null).in(null).in(stick)
                    .out(arrow)
                    .build();
            context.register(recipe2);
        }
    }
}
