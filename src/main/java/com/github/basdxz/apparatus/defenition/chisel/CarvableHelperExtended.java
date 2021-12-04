package com.github.basdxz.apparatus.defenition.chisel;

import com.github.basdxz.apparatus.defenition.IParaTileManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import team.chisel.api.carving.CarvingUtils;
import team.chisel.api.carving.ICarvingVariation;
import team.chisel.api.carving.IVariationInfo;
import team.chisel.api.carving.VariationInfoBase;
import team.chisel.api.rendering.TextureType;
import team.chisel.shadow.team.chisel.ctmlib.ISubmapManager;

import java.util.HashMap;
import java.util.Map;

public class CarvableHelperExtended {
    private final IParaTileManager manager;
    private final Map<String, IVariationInfo> infoMap = new HashMap<>();

    public CarvableHelperExtended(IParaTileManager manager) {
        this.manager = manager;
    }

    public void addVariation(String tileID, ISubmapManager submapManager) {
        IVariationInfo info;
        if (FMLCommonHandler.instance().getSide().isClient())
            info = getClientInfo(manager.modid(), null, "", manager.block(), submapManager);
        else
            info = getServerInfo(manager.modid(), null, "", manager.block(), submapManager);

        infoMap.put(tileID, info);
    }

    private IVariationInfo getClientInfo(String modid, String texture, String description, Block block, ISubmapManager customManager) {
        ICarvingVariation var = CarvingUtils.getDefaultVariationFor(manager.block(), 0, 0);
        TextureType type = TextureType.getTypeFor(null, modid, texture);
        if (type == TextureType.CUSTOM && customManager == null && block == null) {
            throw new IllegalArgumentException(String.format("Could not find texture %s, and no custom texture manager was provided.", texture));
        }

        ISubmapManager manager;
        if (customManager != null) {
            manager = customManager;
        } else {
            manager = type.createManagerFor(var, texture);
        }
        return new VariationInfoBase(var, description, manager, type);
    }

    private IVariationInfo getServerInfo(String modid, String texture, String description, Block block, ISubmapManager customManager) {
        ICarvingVariation var = CarvingUtils.getDefaultVariationFor(manager.block(), 0, 0);
        return new VariationInfoBase(var, description, null);
    }

    public IVariationInfo getVariation(String tileID) {
        return infoMap.get(tileID);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(String tileID, int side) {
        IVariationInfo info = infoMap.get(tileID);
        if (info == null)
            return getMissingIcon();
        return info.getIcon(side, 0);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getMissingIcon() {
        return ((TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture))
                .getAtlasSprite("missingno");
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(Block block, IIconRegister register) {
        for (IVariationInfo info : infoMap.values()) {
            info.registerIcons(manager.modid(), block, register);
        }
    }
}
