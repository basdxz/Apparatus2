package com.github.basdxz.paratileentity.defenition.tile;

import com.github.basdxz.paratileentity.defenition.IParaTileManager;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

@Getter
@Accessors(fluent = true)
public abstract class ParaTile implements IParaTile {
    protected final int tileID;
    protected IParaTileManager manager;

    // region Modified Lombok @SuperBuilder with manager parameter excluded
    protected ParaTile(ParaTileBuilder<?, ?> b) {
        this.tileID = b.tileID;
    }

    public static abstract class ParaTileBuilder<C extends ParaTile, B extends ParaTileBuilder<C, B>> {
        private int tileID;

        public B tileID(int tileID) {
            this.tileID = tileID;
            return self();
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "ParaTile.ParaTileBuilder(tileID=" + this.tileID + ")";
        }
    }
    // endregion

    @Override
    public IParaTile clone() {
        try {
            return (IParaTile) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Failed to create IParaTile!");
        }
    }

    @Override
    public void registerManager(IParaTileManager manager) {
        if (this.manager != null)
            throw new IllegalStateException("Manager already registered.");
        this.manager = manager;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
    }

    @Override
    public IIcon getIcon(ForgeDirection side) {
        return null;
    }

    @Override
    public boolean singleton() {
        return false;
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void updateEntity() {
    }
}
