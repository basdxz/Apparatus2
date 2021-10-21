package com.github.basdxz.paratileentity.defenition.managed;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.minecraft.tileentity.TileEntity;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

@Getter
@Accessors(fluent = true)
public final class ParaTileEntity extends ParaTileEntityAbstract {
    private static IParaTileEntity INSTANCE;

    public static IParaTileEntity registerTileEntity(IParaTileManager manager) {
        if (INSTANCE != null)
            throw new RuntimeException("ParaTileEntity already registered!");
        GameRegistry.registerTileEntity(ParaTileEntity.class, MODID + ":ParaTileEntity");
        return INSTANCE = new ParaTileEntity(manager);
    }

    public ParaTileEntity() {
        super(INSTANCE.manager());
    }

    private ParaTileEntity(IParaTileManager manager) {
        super(manager);
    }

    @Override
    public TileEntity newTileEntity() {
        return new ParaTileEntity();
    }
}
