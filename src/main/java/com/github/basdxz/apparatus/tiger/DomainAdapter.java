package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.IInitializeable;
import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.entity.IItem;
import com.github.basdxz.apparatus.common.entity.ITile;
import com.github.basdxz.apparatus.common.loader.IDomainLoader;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponent;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DomainAdapter implements IInitializeable {
    protected final IDomain domain;
    protected final String[] packageNames;
    protected IDomainLoader loader;

    public DomainAdapter(@NonNull IDomain domain, @NonNull String... packageNames) {
        this.domain = domain;
        this.packageNames = packageNames;
    }

    @Override
    public void preInit() {
        loader = domain.newLoader(packageNames);
        loader.preInit();

        domain.entities().forEach(this::adapt);
    }

    //TODO: Rethink this
    public Object adapt(@NonNull IEntity paraThing) {
        if (paraThing instanceof ITile)
            return new ParaBlockAdapter((ITile) paraThing);
        if (paraThing instanceof IItem)
            return new ParaItemAdapter((IItem) paraThing);
        return null;
    }

    @Override
    public void init() {
        loader.init();

//        for (IRecipe recipe : manager.recipes()) {
//            System.out.println(recipe);
//
//            val output = findItem(recipe.output().paraID())
//                    .orElseThrow(() -> new NullPointerException("HAGRID YOU FAT OAF YOU ATE ALL THE FOODS!!"));
//
//            if (recipe.type().equals(SHAPELESS)) {
//                GameRegistry.addShapelessRecipe(
//                        new ItemStack(output),
//                        (Object[]) adaptShapelessInputs(recipe.inputs())
//                );
//            } else if (recipe.type().equals(SHAPED)) {
//                GameRegistry.addShapedRecipe(new ItemStack(output), adaptShapedInputs(recipe.inputs()));
//            }
//        }
    }

    protected ItemStack[] adaptShapelessInputs(@NonNull List<IRecipeComponent> components) {
        val adaptedInputs = new ItemStack[components.size()];
        for (var i = 0; i < adaptedInputs.length; i++) {
            val component = components.get(i);
            if (component == null)
                throw new NullPointerException("Components can't be null!");
            adaptedInputs[i] = new ItemStack(
                    findItem(component.paraID()).orElseThrow(() -> new NullPointerException("Item Remap Failed!")));
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

            val item = findItem(component.paraID());
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
    public void postInit() {
        loader.postInit();

        loader = null;
    }
}
