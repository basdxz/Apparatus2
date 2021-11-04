package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.ParaTileEntityMod;
import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.github.basdxz.paratileentity.util.Utils;
import lombok.SneakyThrows;
import lombok.val;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.TILE_ID_INT_NBT_TAG;
import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.isNBTFromParaTileEntity;
import static com.github.basdxz.paratileentity.defenition.tile.IProxiedItemBlock.BLOCK_UPDATE_FLAG;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {
    @Shadow
    private Minecraft gameController;

    /*
        Injects right before a tile entity would-be synced from the server to
        make sure our data is ready to be loaded just in time.
     */
    @Inject(method = "handleUpdateTileEntity(Lnet/minecraft/network/play/server/S35PacketUpdateTileEntity;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/multiplayer/WorldClient;getTileEntity " +
                            "(III)Lnet/minecraft/tileentity/TileEntity;",
                    shift = At.Shift.BEFORE),
            require = 1)
    private void handleUpdateTileEntity(S35PacketUpdateTileEntity tileEntityUpdatePacket, CallbackInfo ci) {
        if (isNBTFromParaTileEntity(tileEntityUpdatePacket.func_148857_g()))
            bufferTile(gameController.theWorld,
                    tileEntityUpdatePacket.func_148856_c(),
                    tileEntityUpdatePacket.func_148855_d(),
                    tileEntityUpdatePacket.func_148854_e(),
                    tileEntityUpdatePacket.func_148857_g());
    }

    /*
        Tosses a reference ParaTile into the managers buffer.
    */
    @SneakyThrows
    private static void bufferTile(World world, int posX, int posY, int posZ, NBTTagCompound nbtFocus) {
        val block = world.getBlock(posX, posY, posZ);
        if (!(block instanceof IParaBlock))
            return;

        val tileEntity = getTileEntity(world, posX, posY, posZ);
        val tileID = nbtFocus.getInteger(TILE_ID_INT_NBT_TAG);

        if (tileEntity instanceof IParaTileEntity) {
            if (tileID != ((IParaTileEntity) tileEntity).tileID())
                world.setBlock(posX, posY, posZ, block, tileID, BLOCK_UPDATE_FLAG);
            return;
        }

        ((IParaBlock) block).manager().bufferedTile(world, posX, posY, posZ, tileID);
        ParaTileEntityMod.debug("Preloaded ParaTile from NBT: " + tileID);
    }

    /*
        Gets a TileEntity from world, returning null if it doesn't exist.
    */
    private static TileEntity getTileEntity(World world, int posX, int posY, int posZ) {
        val chunk = world.getChunkFromBlockCoords(posX, posZ);
        if (chunk != null)
            return chunk.getTileEntityUnsafe(
                    Utils.worldToChunkBlockPos(posX),
                    posY,
                    Utils.worldToChunkBlockPos(posZ));
        return null;
    }
}
