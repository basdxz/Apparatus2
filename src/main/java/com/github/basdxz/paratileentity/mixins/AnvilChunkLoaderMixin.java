package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.ParaTileEntityMod;
import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.util.ChunkBlockUtils;
import lombok.val;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.*;

@Mixin(AnvilChunkLoader.class)
public class AnvilChunkLoaderMixin {

    /*
        Injects right before a tile entity would-be read from NBT to make sure our data is ready to be loaded just in time.
     */
    @Inject(method = "loadEntities",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/tileentity/TileEntity;createAndLoadEntity " +
                            "(Lnet/minecraft/nbt/NBTTagCompound;)Lnet/minecraft/tileentity/TileEntity;",
                    shift = At.Shift.BEFORE),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    public void loadEntities(World world, NBTTagCompound nbt, Chunk chunk, CallbackInfo ci,
                             NBTTagList nbt1, NBTTagList nbt2, int i, NBTTagCompound nbt3) {
        if (isNBTFromParaTileEntity(nbt3))
            bufferTile(chunk, nbt3);
    }

    /*
        Tosses a reference ParaTile into the managers buffer.
     */
    private static void bufferTile(Chunk chunk, NBTTagCompound nbtTagCompound) {
        val posX = nbtTagCompound.getInteger(TILE_ENTITY_X_POS_INT_NBT_TAG);
        val posY = nbtTagCompound.getInteger(TILE_ENTITY_Y_POS_INT_NBT_TAG);
        val posZ = nbtTagCompound.getInteger(TILE_ENTITY_Z_POS_INT_NBT_TAG);
        val tileID = nbtTagCompound.getInteger(TILE_ID_INT_NBT_TAG);

        val block = chunk.getBlock(
                ChunkBlockUtils.worldToChunkBlockPos(posX),
                posY,
                ChunkBlockUtils.worldToChunkBlockPos(posZ));
        if (!(block instanceof IParaBlock))
            return;

        val paraBlock = (IParaBlock) block;
        paraBlock.manager().bufferedTile(null, 0, 0, 0, tileID);

        ParaTileEntityMod.debug("Preloaded ParaTile from NBT: " + tileID);
    }
}
