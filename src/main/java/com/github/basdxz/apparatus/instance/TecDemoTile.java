package com.github.basdxz.apparatus.instance;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import com.github.basdxz.apparatus.defenition.RegisterParaTile;
import com.github.basdxz.apparatus.defenition.tile.ParaTile;
import lombok.experimental.SuperBuilder;
import net.minecraft.item.ItemStack;

import static com.github.basdxz.apparatus.ApparatusMod.MODID;

@RegisterParaTile(modid = MODID, manager = "test_tile")
@SuperBuilder
public class TecDemoTile extends ParaTile {
    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + ".tec_demo";
    }

    @Override
    public void register(IParaTileManager manager) {
        manager.registerTile(builder().tileID("Ztec_demo").build());
    }
}
