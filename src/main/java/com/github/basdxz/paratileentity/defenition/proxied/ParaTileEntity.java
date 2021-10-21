package com.github.basdxz.paratileentity.defenition.proxied;

import cpw.mods.fml.common.registry.GameRegistry;
import lombok.NoArgsConstructor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@NoArgsConstructor // Tile Entities Must have an empty constructor
public class ParaTileEntity extends TileEntity implements IParaTileEntity {
    public static void register() {
        GameRegistry.registerTileEntity(ParaTileEntity.class, "ParaTileEntity");
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        System.out.println("susy");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new ParaTileEntity();
    }
}
