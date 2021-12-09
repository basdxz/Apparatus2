package com.github.basdxz.apparatus.util;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.managed.IParaItemBlock;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.Optional;

@UtilityClass
public class Utils {

    /*
        Gets a TileEntity from world without creating it if it doesn't exist.
    */
    public Optional<TileEntity> getTileEntityIfExists(@NonNull final World world, int posX, int posY, int posZ) {
        Optional<TileEntity> tileEntity = Optional.empty();
        val chunk = world.getChunkFromBlockCoords(posX, posZ);
        if (chunk != null)
            tileEntity = getTileEntityIfExists(chunk, worldToChunkBlockPosXZ(posX), posY, worldToChunkBlockPosXZ(posZ));
        return tileEntity;
    }

    /*
        Gets a TileEntity from world without creating it if it doesn't exist.
    */
    public Optional<TileEntity> getTileEntityIfExists(@NonNull final Chunk chunk, int posX, int posY, int posZ) {
        return Optional.ofNullable(chunk.getTileEntityUnsafe(posX, posY, posZ));
    }

    /*
        Converts from world block position to chunk block position for X and Z
    */
    public int worldToChunkBlockPosXZ(final int worldBlockPos) {
        return worldBlockPos & 15;
    }

    public static ItemStack bufferParaTile(ItemStack itemStack) {
        val item = itemStack.getItem();
        if (item instanceof IParaItemBlock) {
            val paraItem = ((IParaItemBlock) item);
            paraItem.manager().bufferedTile(
                    Minecraft.getMinecraft().theWorld, 0, 0, 0, paraItem.tileID(itemStack));
        }
        return itemStack;
    }

    /*
        Safely buffers an IParaTile if it exists.
    */
    public void bufferParaTile(IBlockAccess blockAccess, int posX, int posY, int posZ) {
        bufferParaTile(blockAccess, posX, posY, posZ, blockAccess.getBlock(posX, posY, posZ));
    }

    /*
        Safely buffers an IParaTile if it exists.
    */
    public void bufferParaTile(IBlockAccess blockAccess, int posX, int posY, int posZ, Block block) {
        if (block instanceof IParaBlock)
            ((IParaBlock) block).manager().bufferedTile(((IParaBlock) block).paraTile(blockAccess, posX, posY, posZ));
    }

}
