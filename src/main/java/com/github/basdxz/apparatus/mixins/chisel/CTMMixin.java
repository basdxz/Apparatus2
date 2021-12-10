package com.github.basdxz.apparatus.mixins.chisel;

import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.managed.IParaTileEntity;
import com.github.basdxz.apparatus.defenition.tile.handler.ICTMGroupHandler;
import com.github.basdxz.apparatus.util.Utils;
import com.google.common.base.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import team.chisel.shadow.team.chisel.ctmlib.CTM;

import static net.minecraft.client.Minecraft.getMinecraft;
import static team.chisel.shadow.team.chisel.ctmlib.CTM.disableObscuredFaceCheckConfig;

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
    @SideOnly(Side.CLIENT)
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
    @SideOnly(Side.CLIENT)
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
    @SideOnly(Side.CLIENT)
    private boolean isParaTileConnected(IBlockAccess blockAccess, int posX, int posY, int posZ,
                                        ForgeDirection direction) {
        val cachedParaTile = cachedParaBlock.paraTile(blockAccess, cachedPosX, cachedPosY, cachedPosZ);

        if (!disableObscuredFaceCheck.or(disableObscuredFaceCheckConfig)) {
            val posX2 = posX + direction.offsetX;
            val posY2 = posY + direction.offsetY;
            val posZ2 = posZ + direction.offsetZ;
            val block = blockAccess.getBlock(posX2, posY2, posZ2);

            if (block instanceof IParaBlock) {
                val tileEntity = Utils.getTileEntityIfExists(
                        getMinecraft().theWorld, posX2, posY2, posZ2);
                if (tileEntity.isPresent() && tileEntity.get() instanceof IParaTileEntity) {
                    val paraTile = ((IParaTileEntity) tileEntity.get()).paraTile();

                    // todo proper paratile equals function
                    if (cachedParaTile.tileID().equals(paraTile.tileID()) &&
                            cachedParaTile.manager() == paraTile.manager())
                        return false;

                    if (cachedParaTile instanceof ICTMGroupHandler && paraTile instanceof ICTMGroupHandler) {
                        if (((ICTMGroupHandler) cachedParaTile).CTMGroup().equals(
                                ((ICTMGroupHandler) paraTile).CTMGroup()))
                            return false;
                    }
                }
            }

        }

        val tileEntity = Utils.getTileEntityIfExists(getMinecraft().theWorld, posX, posY, posZ);
        if (!tileEntity.isPresent() || !(tileEntity.get() instanceof IParaTileEntity))
            return false;

        val paraTile = cachedParaBlock.paraTile(blockAccess, posX, posY, posZ);

        // todo proper paratile equals function
        if (cachedParaTile.tileID().equals(paraTile.tileID()) && cachedParaTile.manager() == paraTile.manager())
            return true;

        if (!(cachedParaTile instanceof ICTMGroupHandler && paraTile instanceof ICTMGroupHandler))
            return false;

        return ((ICTMGroupHandler) cachedParaTile).CTMGroup()
                .equals(((ICTMGroupHandler) paraTile).CTMGroup());
    }
}
