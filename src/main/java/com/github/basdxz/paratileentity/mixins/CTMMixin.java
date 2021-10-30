package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.google.common.base.Optional;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.chisel.ctmlib.CTM;

import static team.chisel.ctmlib.CTM.disableObscuredFaceCheckConfig;

@Mixin(CTM.class)
public abstract class CTMMixin {
    @Shadow
    public Optional<Boolean> disableObscuredFaceCheck;
    private IParaBlock cachedParaBlock;
    private int cachedPosX;
    private int cachedPosY;
    private int cachedPosZ;

    @Inject(method = "buildConnectionMap(Lnet/minecraft/world/IBlockAccess;IIIILnet/minecraft/block/Block;I)V",
            at = @At("HEAD"),
            remap = false,
            require = 1)
    public void buildConnectionMap(IBlockAccess world, int posX, int posY, int posZ, int side, Block block, int meta,
                                   CallbackInfo ci) {
        if (block instanceof IParaBlock) {
            cachedParaBlock = (IParaBlock) block;
            cachedPosX = posX;
            cachedPosY = posY;
            cachedPosZ = posZ;
        }
    }

    @Inject(method = "isConnected(Lnet/minecraft/world/IBlockAccess;" +
            "IIILnet/minecraftforge/common/util/ForgeDirection;Lnet/minecraft/block/Block;I)Z",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    public void isConnected(IBlockAccess blockAccess, int posX, int posY, int posZ, ForgeDirection direction,
                            Block block, int blockMeta, CallbackInfoReturnable<Boolean> cir) {
        if (block == cachedParaBlock) {
            cir.setReturnValue(isParaTileConnected(blockAccess, posX, posY, posZ, direction));
            cir.cancel();
        }
    }

    private boolean isParaTileConnected(IBlockAccess blockAccess, int posX, int posY, int posZ,
                                        ForgeDirection direction) {
        if (!disableObscuredFaceCheck.or(disableObscuredFaceCheckConfig)) {
            int posX2 = posX + direction.offsetX;
            int posY2 = posY + direction.offsetY;
            int posZ2 = posZ + direction.offsetZ;

            if (!(blockAccess.getBlock(posX2, posY2, posZ2) == Blocks.air))
                return false;
        }

        if (!(blockAccess.getTileEntity(posX, posY, posZ) instanceof IParaTileEntity))
            return false;

        return cachedParaBlock.tileID(blockAccess, cachedPosX, cachedPosY, cachedPosZ) ==
                cachedParaBlock.tileID(blockAccess, posX, posY, posZ);
    }
}
