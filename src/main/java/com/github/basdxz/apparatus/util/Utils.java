package com.github.basdxz.apparatus.util;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.managed.IParaItemBlock;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import ru.timeconqueror.spongemixins.MinecraftURLClassPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import static com.github.basdxz.apparatus.ParaTileEntityMod.info;

@UtilityClass
public class Utils {
    public boolean isDevelopmentEnvironment() {
        return (boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    }

    public boolean loadJar(final String jarName) {
        try {
            File jar = MinecraftURLClassPath.getJarInModPath(jarName);
            if (jar == null) {
                info("Jar not found: " + jarName);
                return false;
            }

            info("Attempting to add " + jar + " to the URL Class Path");
            if (!jar.exists())
                throw new FileNotFoundException(jar.toString());
            MinecraftURLClassPath.addJar(jar);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
        Gets a TileEntity from world without creating it if it doesn't exist.
    */
    public Optional<TileEntity> getTileEntityIfExists(@NonNull final World world, int posX, int posY, int posZ) {
        Optional<TileEntity> tileEntity = Optional.empty();
        val chunk = world.getChunkFromBlockCoords(posX, posZ);
        if (chunk != null)
            tileEntity = getTileEntityIfExists(chunk,
                    Utils.worldToChunkBlockPosXZ(posX),
                    posY,
                    Utils.worldToChunkBlockPosXZ(posZ));
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

    public static void bufferParaTile(ItemStack itemStack) {
        val item = itemStack.getItem();
        if (item instanceof IParaItemBlock) {
            val paraItem = ((IParaItemBlock) item);
            paraItem.manager().bufferedTile(
                    Minecraft.getMinecraft().theWorld, 0, 0, 0, paraItem.tileID(itemStack));
        }
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