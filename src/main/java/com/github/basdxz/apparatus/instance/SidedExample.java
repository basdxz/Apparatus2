package com.github.basdxz.apparatus.instance;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import com.github.basdxz.apparatus.defenition.RegisterParaTile;
import com.github.basdxz.apparatus.defenition.tile.ParaTile;
import com.github.basdxz.apparatus.defenition.tile.handler.IFacingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.ctmlib.ISubmapManager;

import java.util.Arrays;
import java.util.List;

import static com.github.basdxz.apparatus.ApparatusMod.MODID;

@RegisterParaTile(modid = MODID, manager = "test_tile")
@Accessors(fluent = true)
@SuperBuilder
public class SidedExample extends ParaTile implements IFacingHandler {
    private final static List<IIcon> icons = Arrays.asList(new IIcon[2]);

    @Getter
    @Setter
    @Builder.Default
    public ForgeDirection facing = DEFAULT_INVENTORY_FACING;

    @Override
    public void register(IParaTileManager manager) {
        manager.registerTile(builder().tileID("10").build());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        icons.set(0, iconRegister.registerIcon(MODID + ":test_tile_" + 0));
        icons.set(1, iconRegister.registerIcon(MODID + ":test_tile_" + 1));
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return side == facing ? icons.get(0) : icons.get(1);
    }

    @Override
    public boolean unCloneable() {
        return false;
    }

    @Override
    public ISubmapManager submapManager() {
        return null;
    }
}
