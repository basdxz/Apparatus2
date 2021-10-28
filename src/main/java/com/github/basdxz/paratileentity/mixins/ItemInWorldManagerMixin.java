package com.github.basdxz.paratileentity.mixins;

import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemInWorldManager.class)
public class ItemInWorldManagerMixin {
    @Shadow
    public World theWorld;

    @Inject(method = "tryHarvestBlock",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/server/management/ItemInWorldManager;removeBlock (IIIZ)Z",
                    shift = At.Shift.BEFORE),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void tryHarvestBlockPreRemoveBlock(int posX, int posY, int posZ, CallbackInfoReturnable<Boolean> cir,
                                              BlockEvent.BreakEvent event, ItemStack stack, Block block, int l,
                                              boolean flag, ItemStack itemstack, boolean flag1) {
        if (block instanceof IParaBlock)
            bufferTile((IParaBlock) block, theWorld, posX, posY, posZ);
    }

    /*
        Tosses a reference ParaTile into the managers buffer.
     */
    private static void bufferTile(IParaBlock paraBlock, IBlockAccess blockAccess, int posX, int posY, int posZ) {
        paraBlock.manager().bufferTile(paraBlock.paraTile(blockAccess, posX, posY, posZ));
    }

    @Inject(method = "tryHarvestBlock",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/server/management/ItemInWorldManager;removeBlock (IIIZ)Z",
                    shift = At.Shift.BY,
                    by = 2), // Pushes after the result is stored so our comparison is valid
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void tryHarvestBlockPostRemoveBlock(int posX, int posY, int posZ, CallbackInfoReturnable<Boolean> cir,
                                               BlockEvent.BreakEvent event, ItemStack stack, Block block, int l,
                                               boolean flag, ItemStack itemstack, boolean flag1) {
        if (block instanceof IParaBlock && failedHarvesting(flag, flag1))
            purgeBufferTile((IParaBlock) block);
    }

    /*
        Checks if the harvest had failed (main block dropped or not)
     */
    private static boolean failedHarvesting(boolean flag, boolean flag1) {
        return !(flag && flag1);
    }

    /*
        Removes a reference ParaTile from the managers buffer.
     */
    private static void purgeBufferTile(IParaBlock paraBlock) {
        paraBlock.manager().bufferTile();
    }
}
