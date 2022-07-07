package com.github.basdxz.apparatus.adapter.registry.impl;

import com.github.basdxz.apparatus.adapter.entity.IEntityAdapter;
import com.github.basdxz.apparatus.adapter.fluid.impl.FluidAdapter;
import com.github.basdxz.apparatus.adapter.item.impl.ItemAdapter;
import com.github.basdxz.apparatus.adapter.registry.IRegistryAdapter;
import com.github.basdxz.apparatus.adapter.tile.impl.TileAdapter;
import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.entity.IFluid;
import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.common.entity.ITile;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponent;
import com.github.basdxz.apparatus.common.resource.IResource;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.github.basdxz.apparatus.common.recipe.impl.RecipeType.SHAPED;
import static com.github.basdxz.apparatus.common.recipe.impl.RecipeType.SHAPELESS;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
public class RegistryAdapter implements IRegistryAdapter {
    public static final IRegistryAdapter INSTANCE = new RegistryAdapter();

    protected static final String REGISTRY_ADAPTER_NAME = "minecraft_forge_adapter";

    protected final List<IEntityAdapter> entityAdapters = new ArrayList<>();

    @Override
    public String registryName() {
        return REGISTRY_ADAPTER_NAME;
    }

    @Override
    public void register(@NonNull IEntity entity) {
        adapt(entity).ifPresent(entityAdapters::add);
    }

    //TODO: Rethink this
    protected Optional<IEntityAdapter> adapt(@NonNull IEntity entity) {
        if (entity instanceof IFluid)
            return Optional.of(new FluidAdapter((IFluid) entity));
        if (entity instanceof ITile)
            return Optional.of(new TileAdapter((ITile) entity));
        if (entity instanceof IItem)
            return Optional.of(new ItemAdapter((IItem) entity));
        return Optional.empty();
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        System.out.println(recipe);
        val output = findItem(recipe.output().entityID())
                .orElseThrow(() -> new NullPointerException("HAGRID YOU FAT OAF YOU ATE ALL THE FOODS!!"));

        if (recipe.type().equals(SHAPELESS)) {
            GameRegistry.addShapelessRecipe(
                    new ItemStack(output),
                    (Object[]) adaptShapelessInputs(recipe.inputs())
            );
        } else if (recipe.type().equals(SHAPED)) {
            GameRegistry.addShapedRecipe(new ItemStack(output), adaptShapedInputs(recipe.inputs()));
        }
    }

    protected ItemStack[] adaptShapelessInputs(@NonNull List<IRecipeComponent> components) {
        val adaptedInputs = new ItemStack[components.size()];
        for (var i = 0; i < adaptedInputs.length; i++) {
            val component = components.get(i);
            if (component == null)
                throw new NullPointerException("Components can't be null!");
            adaptedInputs[i] = new ItemStack(
                    findItem(component.entityID()).orElseThrow(() -> new NullPointerException("Item Remap Failed!")));
        }
        return adaptedInputs;
    }

    protected Object[] adaptShapedInputs(@NonNull List<IRecipeComponent> components) {
        val adaptedInputs = new ArrayList<>();
        adaptedInputs.add("012");
        adaptedInputs.add("345");
        adaptedInputs.add("678");
        for (var i = 0; i < 9; i++) {
            val component = components.get(i);
            if (component == null)
                continue;

            val item = findItem(component.entityID());
            if (!item.isPresent())
                continue;

            adaptedInputs.add(Character.forDigit(i, 10));
            adaptedInputs.add(new ItemStack(item.get()));
        }
        return adaptedInputs.toArray();
    }

    protected Optional<Item> findItem(@NonNull IEntityID entityID) {
        return Optional.ofNullable(GameRegistry.findItem(entityID.domain().domainName(), entityID.entityName()));
    }

    @Override
    public void register(@NonNull IResource resource) {
    }

    @Override
    public List<IEntityAdapter> entityAdapters() {
        return Collections.unmodifiableList(entityAdapters);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return registryToString();
    }
}
