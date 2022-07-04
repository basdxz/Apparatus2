package com.github.basdxz.apparatus.tiger;

import com.github.basdxz.apparatus.common.loader.IInitializeable;
import com.github.basdxz.apparatus.common.parathing.IParaBlock;
import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.registry.IParaID;
import com.github.basdxz.apparatus.common.registry.IParaManager;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.*;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Optional;

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
    public Object adapt(@NonNull IParaThing paraThing) {
        if (paraThing instanceof IParaBlock)
            return new ParaBlockAdapter((IParaBlock) paraThing);
        if (paraThing instanceof IParaItem)
            return new ParaItemAdapter((IParaItem) paraThing);
        return null;
    }

    @Override
    public void init() {
        manager.init();

        for (IRecipe recipe : manager.recipes()) {
            System.out.println(recipe);

            val input = findItem(recipe.ingredients().get(0).paraID()).orElse(Items.cake);
            val output = findItem(recipe.results().get(0).paraID()).orElse(Items.cake);

            GameRegistry.addShapelessRecipe(new ItemStack(output), new ItemStack(input));
        }
    }

    public static Optional<Item> findItem(@NonNull IParaID paraID) {
        return Optional.ofNullable(GameRegistry.findItem(paraID.registry().registryName(), paraID.paraName()));
    }

    @Override
    public void postInit() {
        manager.postInit();
    }
}
