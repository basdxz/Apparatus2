package com.github.basdxz.apparatus.fx;

import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

@SideOnly(Side.CLIENT)
public class EntityBlockDustFXParaTile extends EntityDiggingFXParaTile {
    public EntityBlockDustFXParaTile(World world, double posX, double posY, double posZ,
                                     double motionX, double motionY, double motionZ,
                                     IParaTile tile) {
        this(world, posX, posY, posZ,
                motionX, motionY, motionZ, tile, ForgeDirection.getOrientation(world.rand.nextInt(6)));
    }

    public EntityBlockDustFXParaTile(World world, double posX, double posY, double posZ,
                                     double motionX, double motionY, double motionZ,
                                     IParaTile tile, ForgeDirection direction) {
        super(world, posX, posY, posZ, motionX, motionY, motionZ, tile, direction);
        this.setParticleIcon(tile.getIcon(direction));
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
    }
}
