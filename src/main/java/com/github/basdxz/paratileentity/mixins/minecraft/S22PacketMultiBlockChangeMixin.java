package com.github.basdxz.paratileentity.mixins.minecraft;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.github.basdxz.paratileentity.network.MultiParaTileChange;
import com.github.basdxz.paratileentity.network.Pos3;
import com.github.basdxz.paratileentity.util.Utils;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

// Server-Side
@Mixin(S22PacketMultiBlockChange.class)
public class S22PacketMultiBlockChangeMixin {
    protected final Map<Pos3, Integer> paraTileIDs = new HashMap<>();

    @Redirect(method = "<init>(I[SLnet/minecraft/world/chunk/Chunk;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/chunk/Chunk;getBlock (III)Lnet/minecraft/block/Block;"),
            require = 1)
    private Block getBlockPassthrough(Chunk instance, int posX, int posY, int posZ) {
        val block = instance.getBlock(posX, posY, posZ);
        if (block instanceof IParaBlock) {
            val tileEntity = Utils.getTileEntityIfExists(instance, posX, posY, posZ);
            if (tileEntity.isPresent() && (tileEntity.get() instanceof IParaTileEntity))
                paraTileIDs.put(new Pos3(posX, posY, posZ), ((IParaTileEntity) tileEntity.get()).tileID());
        }
        return block;
    }

    @Inject(method = "<init>(I[SLnet/minecraft/world/chunk/Chunk;)V",
            at = @At(value = "RETURN",
                    target = "Ljava/io/ByteArrayOutputStream;toByteArray ()[B"),
            require = 1)
    private void getBlockPostPayloadCreation(int i1, short[] j1, Chunk k, CallbackInfo ci) {
        if (paraTileIDs.isEmpty())
            return;
        MultiParaTileChange.bufferedMultiParaTileChange(new HashMap<>(paraTileIDs));
        paraTileIDs.clear();
    }
}
