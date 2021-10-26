package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@Accessors(fluent = true)
@SuperBuilder
public abstract class ParaTile implements IParaTile {
    protected final int tileID;
    protected IParaTileManager manager;
    protected IParaTileEntity tileEntity;

    @Override
    public void init(IParaTileManager manager) {
        if (manager == null)
            throw new IllegalStateException("Already initialised!");
        this.manager = manager;
    }

    @Override
    public IParaTile clone() {
        try {
            return (IParaTile) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Failed to create IParaTile!");
        }
    }
}
