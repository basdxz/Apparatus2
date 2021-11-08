package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.github.basdxz.paratileentity.util.Utils;
import lombok.val;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.TILE_ID_INT_NBT_TAG;
import static com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity.isNBTFromParaTileEntity;
import static com.github.basdxz.paratileentity.defenition.tile.IProxiedItemBlock.BLOCK_UPDATE_FLAG;

// Client-Side
@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {
    @Shadow
    private Minecraft gameController;

    /*
        Buffer the IParaTile when we get a TileEntity update packet from the server relating to an IParaTileEntity.
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
        Stores an IParaTile in the buffer
    */
    private static void bufferTile(World world, int posX, int posY, int posZ, NBTTagCompound nbtFocus) {
        val block = world.getBlock(posX, posY, posZ);
        if (!(block instanceof IParaBlock))
            return;

        val tileEntity = Utils.getTileEntityIfExists(world, posX, posY, posZ);
        val tileID = nbtFocus.getInteger(TILE_ID_INT_NBT_TAG);

        if (tileEntity.isPresent() && tileEntity.get() instanceof IParaTileEntity) {
            // This has mostly happened in testing with mutliblock packet updates
            if (tileID != ((IParaTileEntity) tileEntity.get()).tileID())
                world.setBlock(posX, posY, posZ, block, tileID, BLOCK_UPDATE_FLAG);
            return;
        }

        ((IParaBlock) block).manager().bufferedTile(world, posX, posY, posZ, tileID);
    }
}