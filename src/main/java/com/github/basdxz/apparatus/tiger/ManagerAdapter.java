package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.loader.IInitializeable;
import com.github.basdxz.apparatus.common.parathing.IEntity;
import com.github.basdxz.apparatus.common.parathing.IItem;
import com.github.basdxz.apparatus.common.parathing.ITile;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.recipe.IRecipeComponent;
import com.github.basdxz.apparatus.common.registry.IEntityID;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.github.basdxz.apparatus.common.recipe.impl.RecipeType.SHAPED;
import static com.github.basdxz.apparatus.common.recipe.impl.RecipeType.SHAPELESS;

public class ManagerAdapter implements IInitializeable {
    protected final IParaManager manager;

    public ManagerAdapter(@NonNull IParaManager manager) {
        this.manager = manager;
    }

    @Override
    public void preInit() {
        manager.preInit();
        manager.paraThings().forEach(this::adapt);
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
        manager.init();

        for (IRecipe recipe : manager.recipes()) {
            System.out.println(recipe);

            val output = findItem(recipe.output().paraID())
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

    protected Optional<Item> findItem(@NonNull IEntityID paraID) {
        return Optional.ofNullable(GameRegistry.findItem(paraID.registry().registryName(), paraID.paraName()));
    }

    @Override
    public void postInit() {
        manager.postInit();
    }
}
