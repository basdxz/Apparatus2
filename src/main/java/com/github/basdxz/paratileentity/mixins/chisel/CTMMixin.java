package com.github.basdxz.paratileentity.mixins.chisel;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.github.basdxz.paratileentity.defenition.tile.ICTMGroupHandler;
import com.google.common.base.Optional;
import lombok.val;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.chisel.api.ICarvable;
import team.chisel.ctmlib.CTM;

import static team.chisel.ctmlib.CTM.disableObscuredFaceCheckConfig;

// Client-Side
@Mixin(CTM.class)
public class CTMMixin {
    @Shadow
    public Optional<Boolean> disableObscuredFaceCheck;
    private IParaBlock cachedParaBlock;
    private int cachedPosX;
    private int cachedPosY;
    private int cachedPosZ;

    /*
        Caches IParaBlocks on each buildConnectionMap call.
     */
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

    /*
        Redirects and returns connection tests against IParaBlocks.

        The given block is the one to compare against, not our own block.
     */
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

    /*
        Performs the connection check but using our own logic and comparing tileIDs instead of blockMetas.

        Also implements a CTMGroup check to allow IParaTiles of different ids and managers to connect to each other.
     */
    private boolean isParaTileConnected(IBlockAccess blockAccess, int posX, int posY, int posZ,
                                        ForgeDirection direction) {
        if (!disableObscuredFaceCheck.or(disableObscuredFaceCheckConfig)) {
            int posX2 = posX + direction.offsetX;
            int posY2 = posY + direction.offsetY;
            int posZ2 = posZ + direction.offsetZ;

            if (blockAccess.getBlock(posX2, posY2, posZ2) instanceof ICarvable)
                return false;
        }

        if (!(blockAccess.getTileEntity(posX, posY, posZ) instanceof IParaTileEntity))
            return false;

        val cachedParaTile = cachedParaBlock.paraTile(blockAccess, cachedPosX, cachedPosY, cachedPosZ);
        val paraTile = cachedParaBlock.paraTile(blockAccess, posX, posY, posZ);

        // todo proper paratile equals function
        if (cachedParaTile.tileID() == paraTile.tileID() && cachedParaTile.manager() == paraTile.manager())
            return true;

        if (!(cachedParaTile instanceof ICTMGroupHandler && paraTile instanceof ICTMGroupHandler))
            return false;

        return ((ICTMGroupHandler) cachedParaTile).CTMGroup()
                .equals(((ICTMGroupHandler) paraTile).CTMGroup());
    }
}
