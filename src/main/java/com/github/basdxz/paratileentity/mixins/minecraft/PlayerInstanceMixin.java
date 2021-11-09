package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.network.MultiParaTileChangeMessage;
import com.github.basdxz.paratileentity.network.NetworkDispatcher;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import lombok.val;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.world.ChunkCoordIntPair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static com.github.basdxz.paratileentity.network.MultiParaTileChange.bufferedMultiParaTileChange;
import static com.github.basdxz.paratileentity.network.MultiParaTileChange.bufferedPacketNotNull;
import static org.spongepowered.asm.mixin.injection.At.Shift.AFTER;

@Mixin(PlayerManager.PlayerInstance.class)
public class PlayerInstanceMixin {
    @Shadow
    @Final
    private List playersWatchingChunk;
    @Shadow
    @Final
    private ChunkCoordIntPair chunkLocation;

    @Inject(method = "sendChunkUpdate()V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/network/play/server/S22PacketMultiBlockChange;<init>" +
                            " (I[SLnet/minecraft/world/chunk/Chunk;)V",
                    shift = AFTER),
            require = 1)
    private void sendChunkUpdatePreSendingBlockChangePacket(CallbackInfo ci) {
        System.out.println("Time to sent the pre update packet!");

        if (bufferedPacketNotNull())
            sendToAllPlayersWatchingChunk(
                    playersWatchingChunk,
                    chunkLocation,
                    new MultiParaTileChangeMessage.MultiParaTileChangeData(bufferedMultiParaTileChange()));
    }

    private static void sendToAllPlayersWatchingChunk(List playersWatchingChunk, ChunkCoordIntPair chunkLocation, IMessage message) {
        if (playersWatchingChunk == null) {
            System.out.println("playersWatchingChunk is null!!");
            return;
        }

        if (chunkLocation == null) {
            System.out.println("playersWatchingChunk is chunkLocation!!");
            return;
        }

        for (Object obj : playersWatchingChunk) {
            if (!(obj instanceof EntityPlayerMP))
                return;
            val entityPlayerMP = (EntityPlayerMP) obj;
            if (!entityPlayerMP.loadedChunks.contains(chunkLocation))
                NetworkDispatcher.INSTANCE.sendTo(message, entityPlayerMP);
        }
    }
}
