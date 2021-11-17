package com.github.basdxz.apparatus.mixins.minecraft;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// Server-Side
@Mixin(ItemInWorldManager.class)
public class ItemInWorldManagerMixin {
    @Shadow
    public World theWorld;

    /*
        Buffers an IParaTile before the block is about to be removed during harvesting.

        Used for letting the IParaTile handle the block drops.
     */
    @Inject(method = "tryHarvestBlock",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/server/management/ItemInWorldManager;removeBlock (IIIZ)Z",
                    shift = At.Shift.BEFORE),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    public void tryHarvestBlockPreRemoveBlock(int posX, int posY, int posZ, CallbackInfoReturnable<Boolean> cir,
                                              BlockEvent.BreakEvent event, ItemStack stack, Block block, int l,
                                              boolean flag, ItemStack itemstack, boolean flag1) {
        Utils.bufferParaTile(theWorld, posX, posY, posZ, block);
    }

    /*
        Purges the buffered ParaTile if the block wouldn't have been dropped, avoiding errors.

        fixme: Adjust config to avoid error in console on load (maxShiftBy)
     */
    @Inject(method = "tryHarvestBlock",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/server/management/ItemInWorldManager;removeBlock (IIIZ)Z",
                    shift = At.Shift.BY,
                    by = 2), // Pushes after the result is stored so our comparison is valid
            locals = LocalCapture.CAPTURE_FAILEXCEPTION,
            require = 1)
    public void tryHarvestBlockPostRemoveBlock(int posX, int posY, int posZ, CallbackInfoReturnable<Boolean> cir,
                                               BlockEvent.BreakEvent event, ItemStack stack, Block block, int l,
                                               boolean flag, ItemStack itemstack, boolean flag1) {
        if (block instanceof IParaBlock && failedHarvesting(flag, flag1))
            purgeBufferTile(((IParaBlock) block).manager());
    }

    /*
        Checks if the harvest had failed, resulting in the block itself not being dropped.
     */
    private static boolean failedHarvesting(boolean flag, boolean flag1) {
        return !(flag && flag1);
    }

    /*
        Removes a reference ParaTile from the managers buffer by calling the buffer and ignoring the output.
     */
    private static void purgeBufferTile(IParaTileManager manager) {
        manager.bufferedTile();
    }
}
