package com.github.basdxz.paratileentity.instance;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.ParaTileManager;
import com.github.basdxz.paratileentity.defenition.managed.ParaTileEntityBase;
import lombok.NoArgsConstructor;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;

/*
    Class is intended to be copied for each manager/block id
 */
@NoArgsConstructor
public final class ParaTileEntity extends ParaTileEntityBase {
    // Keep this field name, it is registered via ParaTileManager
    public static IParaTileManager MANAGER;

    /*
        Intended to be run during FMLInitializationEvent.
     */
    public static void load() {
        if (MANAGER != null)
            throw new IllegalStateException("Manager already registered!");
        // ATTENTION: If you copied this class, make sure to change *ALL* of these arguments to match your mod.
        new ParaTileManager(MODID, "test_tile", "com.github.basdxz.paratileentity", ParaTileEntity.class);
    }

    @Override
    public IParaTileManager manager() {
        return MANAGER;
    }
}
