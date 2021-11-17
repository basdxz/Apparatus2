package com.github.basdxz.apparatus.defenition.tile;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import com.github.basdxz.apparatus.defenition.chisel.IChiselRendering;
import com.github.basdxz.apparatus.defenition.managed.IParaTileEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import team.chisel.ctmlib.ISubmapManager;

@Setter
@Getter
@Accessors(fluent = true)
@SuperBuilder
public abstract class ParaTile implements IParaTile, IChiselRendering {
    protected final String tileID;
    protected IParaTileManager manager;
    protected IParaTileEntity tileEntity;

    @Override
    public void init(@NonNull IParaTileManager manager) {
        if (this.manager != null)
            throw new IllegalStateException("Already initialised!");
        this.manager = manager;
    }

    // TODO: Remove from here and force all extending classes to include it.
    @Override
    public void register(IParaTileManager manager) {
    }

    // TODO: Remove from here and force all extending classes to include it.
    @Override
    public void registerRecipes() {
    }

    @Override
    public IParaTile clone() {
        try {
            return (IParaTile) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Failed to create IParaTile!");
        }
    }

    // TODO: Remove once rendering is more settled.
    @Override
    public ISubmapManager submapManager() {
        return null;
    }
}
