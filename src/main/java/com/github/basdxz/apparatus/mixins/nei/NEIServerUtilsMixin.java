package com.github.basdxz.apparatus.mixins.nei;

import codechicken.nei.NEIServerUtils;
import com.github.basdxz.apparatus.defenition.managed.IParaItemBlock;
import lombok.val;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NEIServerUtils.class)
public class NEIServerUtilsMixin {
    @Inject(method = "areStacksSameTypeCrafting(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z",
            at = @At(value = "HEAD"),
            require = 1,
            cancellable = true,
            remap = false)
    private static void areStacksSameTypeCraftingPatch(ItemStack itemStack1, ItemStack itemStack2,
                                                       CallbackInfoReturnable<Boolean> cir) {

        if (itemStack1 == null || itemStack2 == null)
            return;

        val item1 = itemStack1.getItem();
        val item2 = itemStack2.getItem();
        if (!(item1 instanceof IParaItemBlock) || !(item2 instanceof IParaItemBlock))
            return;

        val paraItem1 = ((IParaItemBlock) item1);
        val paraItem2 = ((IParaItemBlock) item2);
        cir.setReturnValue(paraItem1.manager() == paraItem2.manager() &&
                paraItem1.tileID(itemStack1).equals(paraItem2.tileID(itemStack2)));
        cir.cancel();
    }
}
