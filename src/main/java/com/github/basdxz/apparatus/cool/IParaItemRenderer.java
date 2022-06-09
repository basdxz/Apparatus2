package com.github.basdxz.apparatus.cool;

import lombok.*;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public interface IParaItemRenderer extends IItemRenderer {
    @Override
    default boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    default boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        switch (type) {
            case ENTITY:
                switch (helper) {
                    case ENTITY_BOBBING:
                    case ENTITY_ROTATION:
                        return true;
                }
        }
        return false;
    }


    @Override
    default void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case ENTITY:
                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityItem)
                    renderAsEntity((RenderBlocks) data[0], (EntityItem) data[1]);
                break;
            case EQUIPPED:
                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityLivingBase)
                    renderAsEquipped((RenderBlocks) data[0], (EntityLivingBase) data[1]);
                break;
            case EQUIPPED_FIRST_PERSON:
                if (data[0] instanceof RenderBlocks && data[1] instanceof EntityLivingBase)
                    renderAsEquippedFirstPerson((RenderBlocks) data[0], (EntityLivingBase) data[1]);
                break;
            case INVENTORY: {
                if (data[0] instanceof RenderBlocks)
                    renderInInventory((RenderBlocks) data[0]);
            }
            case FIRST_PERSON_MAP: {
                if (data[0] instanceof RenderBlocks)
                    renderInInventory((RenderBlocks) data[0]);
            }
        }
    }

    void renderAsEntity(@NonNull RenderBlocks renderBlocks, @NonNull EntityItem entityItem);

    void renderAsEquipped(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase);

    void renderAsEquippedFirstPerson(@NonNull RenderBlocks renderBlocks, @NonNull EntityLivingBase entityLivingBase);

    void renderInInventory(@NonNull RenderBlocks renderBlocks);

    void renderAsFirstPersonMap(@NonNull RenderBlocks renderBlocks);
}
