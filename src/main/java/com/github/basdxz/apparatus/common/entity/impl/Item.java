package com.github.basdxz.apparatus.common.entity.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.common.example.Externals;
import com.github.basdxz.apparatus.common.loader.IEntityLoader;
import com.github.basdxz.apparatus.common.loader.IInitContext;
import com.github.basdxz.apparatus.common.loader.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.Loader;
import com.github.basdxz.apparatus.common.recipe.impl.Recipe;
import com.github.basdxz.apparatus.common.recipe.impl.RecipeComponent;
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
public class Item implements IItem {
    protected final IEntityID entityID;
    protected final String localizedName;
    protected final Map<IRendererView, IRenderer> renderers;

    public Item(@NonNull IEntityID entityID, @NonNull String localizedName) {
        this.entityID = entityID;
        this.localizedName = localizedName;

        val renderer = newDefaultSpriteRenderer(entityID.domain(), "SwInner");
        renderers = new HashMap<>();
        renderers.put(ENTITY, renderer);
        renderers.put(EQUIPPED, renderer);
        renderers.put(EQUIPPED_FIRST_PERSON, renderer);
        renderers.put(INVENTORY, renderer);
    }

    @NoArgsConstructor
    @Loader(domainName = "test_registry")
    public static class ItemLoader implements IEntityLoader<Item> {
        @Override
        public void preInit(@NonNull IPreInitContext<Item> context) {
            context.register(new Item(context.newParaID("woag_my_item"), "Woag My Item"));
            context.register(new Item(context.newParaID("woag_my_item2"), "Woag My Item"));
            context.register(new Item(context.newParaID("woag_my_item3"), "Woag My Item"));
        }

        @Override
        public void init(@NonNull IInitContext<Item> context) {
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
