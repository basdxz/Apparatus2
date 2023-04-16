package com.github.basdxz.apparatus.instance;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import com.github.basdxz.apparatus.defenition.RegisterParaTile;
import com.github.basdxz.apparatus.defenition.tile.ParaTile;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.experimental.SuperBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.client.render.SubmapManagerBaseExtra;
import team.chisel.ctmlib.ISubmapManager;
import team.chisel.ctmlib.TextureSubmap;

import static com.github.basdxz.apparatus.ApparatusMod.MODID;

@RegisterParaTile(modid = MODID, manager = "test_tile")
@SuperBuilder
public class TecDemoTile extends ParaTile {
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + ".tec_demo";
    }

    @Override
    public void register(IParaTileManager manager) {
        manager.registerTile(builder().tileID("Ztec_demo").build());
    }

    @Override
    public void registerRecipes() {
        GameRegistry.addRecipe(newItemStack(), "WWW", " W ", "WW ", 'W', Items.leather);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        registerChiselBlockIcons();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ISubmapManager submapManager() {
        return new SubmapManagerBaseExtra("") {
            @Override
            public void registerIcons(String modName, Block block, IIconRegister register) {
                submapSmall = new TextureSubmap(Blocks.stained_glass.getIcon(0, 5), 2, 2);
            }
        };
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ForgeDirection side) {
        return getChiselIcon(side);
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
