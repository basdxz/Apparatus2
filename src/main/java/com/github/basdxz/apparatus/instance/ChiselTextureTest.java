package com.github.basdxz.apparatus.instance;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import com.github.basdxz.apparatus.defenition.RegisterParaTile;
import com.github.basdxz.apparatus.defenition.chisel.IChiselRendering;
import com.github.basdxz.apparatus.defenition.tile.ParaTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import team.chisel.client.render.SubmapMultiManager;
import team.chisel.ctmlib.ISubmapManager;

import static com.github.basdxz.apparatus.ApparatusMod.MODID;


@RegisterParaTile(modid = MODID, manager = "test_tile")
@Accessors(fluent = true)
@SuperBuilder
public class ChiselTextureTest extends ParaTile implements IChiselRendering {
    @Getter
    @Setter
    protected ISubmapManager submapManager;

    @Override
    public void register(IParaTileManager manager) {
        manager.registerTile(builder().tileID("wogii").build());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        registerChiselBlockIcons();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ISubmapManager newSubmapManager() {
        return SubmapMultiManager.ofGlowCTM("futura/screenMetallic");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ForgeDirection side) {
        return getChiselIcon(side);
    }
}
