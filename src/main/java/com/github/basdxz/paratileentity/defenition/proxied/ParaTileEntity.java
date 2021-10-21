package com.github.basdxz.paratileentity.defenition.proxied;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import cpw.mods.fml.common.registry.GameRegistry;
import lombok.NoArgsConstructor;
import lombok.val;
import net.minecraft.tileentity.TileEntity;

@NoArgsConstructor // Needed for Server-Side TE init
public class ParaTileEntity extends TileEntity implements IParaTileEntity {
    static {
        GameRegistry.registerTileEntity(ParaTileEntity.class, "ParaTileEntity");
    }

    protected IParaTileManager manager;

    public ParaTileEntity(IParaTileManager manager) {
        this.manager = manager;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (manager != null) {
            System.out.println(manager.getName());
        } else {
            System.out.println("No ParaTileManager??");
        }
    }

    @Override
    public boolean canUpdate() {
        registerManager(); // Included here as it is run after the no args constructor is
        return true;
    }

    protected void registerManager() {
        if(manager == null) {
            val block = getBlockType();
            if (!(block instanceof IParaBlock))
                throw new IllegalStateException("Bound block hasn't implemented IParaItemBlock");
            manager = ((IParaBlock)block).getManager();
        }
    }

    @Override
    public TileEntity createNewTileEntity() {
        return new ParaTileEntity(manager);
    }
}
