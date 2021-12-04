package com.github.basdxz.apparatus.instance;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import com.github.basdxz.apparatus.defenition.RegisterParaTile;
import com.github.basdxz.apparatus.defenition.tile.ParaTile;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.experimental.SuperBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.client.render.SubmapManagerBaseExtra;
import team.chisel.shadow.team.chisel.ctmlib.ISubmapManager;
import team.chisel.shadow.team.chisel.ctmlib.TextureSubmap;

import static com.github.basdxz.apparatus.ApparatusMod.MODID;

@RegisterParaTile(modid = MODID, manager = "test_tile")
@SuperBuilder
public class SimpleTile extends ParaTile {
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + ".my_simple_bricks";
    }

    @Override
    public void register(IParaTileManager manager) {
        manager.registerTile(builder().tileID("12").build());
    }

    @Override
    public void registerRecipes() {
        GameRegistry.addRecipe(newItemStack(), "WWW", " W ", " W ", 'W', Blocks.planks);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        registerChiselBlockIcons();
    }

    @Override
    public ISubmapManager submapManager() {
        return new SubmapManagerBaseExtra("") {
            @Override
            public void registerIcons(String modName, Block block, IIconRegister register) {
                submapSmall = new TextureSubmap(Blocks.brick_block.getIcon(0, 0), 2, 2);
            }
        };
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return getChiselIcon(side);
    }
}
