package com.github.basdxz.apparatus.common.entity.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.common.loader.Loader;
import com.github.basdxz.apparatus.common.loader.context.IInitContext;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.impl.EntityLoader;
import com.github.basdxz.apparatus.common.recipe.impl.Recipe;
import com.github.basdxz.apparatus.common.recipe.impl.RecipeComponent;
import com.github.basdxz.apparatus.example.Externals;
import lombok.*;
import lombok.experimental.*;


@Getter
@Accessors(fluent = true, chain = true)
public class TestItem implements IItem {
    protected final IEntityID entityID;
    protected final String localizedName;

    public TestItem(@NonNull IEntityID entityID, @NonNull String localizedName) {
        this.entityID = entityID;
        this.localizedName = localizedName;
    }

    @NoArgsConstructor
    @Loader(domainName = "apparatus")
    public static class ItemLoader extends EntityLoader<TestItem> {
        @Override
        public void preInit(@NonNull IPreInitContext<TestItem> context) {
            context.register(new TestItem(context.entityID("woag_my_item"), "Woag My Item"));
            context.register(new TestItem(context.entityID("woag_my_item2"), "Woag My Item"));
            context.register(new TestItem(context.entityID("woag_my_item3"), "Woag My Item"));
        }

        @Override
        public void init(@NonNull IInitContext<TestItem> context) {
            //give basdxz minecraft:stick 1
            val stick = RecipeComponent.newRecipeItem(Externals.MINECRAFT_DOMAIN.entityID("stick"));
            val arrow = RecipeComponent.newRecipeItem(Externals.MINECRAFT_DOMAIN.entityID("arrow"));
            val feather = RecipeComponent.newRecipeItem(Externals.MINECRAFT_DOMAIN.entityID("feather"));

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
