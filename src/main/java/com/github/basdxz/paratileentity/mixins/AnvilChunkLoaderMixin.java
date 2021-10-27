package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
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
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void loadEntities(World world, NBTTagCompound nbt1, Chunk chunk, CallbackInfo ci,
                             NBTTagList nbt2, NBTTagList nbt3, int i1, NBTTagCompound nbt4) {
        if (nbt4.getString("id").contains("ParaTileEntity"))
            bufferTile(chunk, nbt4);
    }

    /*
        Tosses a reference ParaTile into the managers buffer.
     */
    private static void bufferTile(Chunk chunk, NBTTagCompound nbtFocus) {
        val posX = nbtFocus.getInteger("x");
        val posY = nbtFocus.getInteger("y");
        val posZ = nbtFocus.getInteger("z");
        val tileID = nbtFocus.getInteger("id");
        val block = chunk.getBlock(posX & 15, posY, posZ & 15);

        if (!(block instanceof IParaBlock))
            return;
        
        val paraBlock = (IParaBlock) block;
        paraBlock.manager().bufferTile(paraBlock.manager().paraTile(tileID));
    }
}
