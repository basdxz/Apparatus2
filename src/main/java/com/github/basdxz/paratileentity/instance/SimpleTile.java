package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.RegisterParaTile;
import com.github.basdxz.paratileentity.defenition.tile.ParaTile;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.experimental.SuperBuilder;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@RegisterParaTile(modid = MODID, manager = "test_tile")
@SuperBuilder
public class SimpleTile extends ParaTile {
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + ".my_simple_bricks";
    }

    @Override
    public void register(IParaTileManager manager) {
        manager.registerTile(builder().tileID(12).build());
    }

    @Override
    public void registerRecipes() {
        GameRegistry.addRecipe(newItemStack(), "WWW", " W ", " W ", 'W', Blocks.planks);
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return Blocks.brick_block.getIcon(0, 0);
    }
}
