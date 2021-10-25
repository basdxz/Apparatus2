package com.github.basdxz.paratileentity.defenition.managed;

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
import team.chisel.ctmlib.ISubmapManager;

import java.util.ArrayList;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.MODID;
import static com.github.basdxz.paratileentity.defenition.IParaTileManager.MAX_TILE_ID;
import static com.github.basdxz.paratileentity.instance.ParaTileEntity.MANAGER;

public class CarvableHelperExtended {
    public static final CarvableHelperExtended INSTANCE = new CarvableHelperExtended();

    public ArrayList<IVariationInfo> infoList = new ArrayList<IVariationInfo>();
    IVariationInfo[] infoMap = new IVariationInfo[MAX_TILE_ID + 1];

    public void addVariation(int tileID, ISubmapManager submapManager) {
        if (infoList.size() >= infoMap.length)
            return;

        IVariationInfo info;
        if (FMLCommonHandler.instance().getSide().isClient())
            info = getClientInfo(MODID, null, "", tileID, null, 0, submapManager, tileID);
        else
            info = getServerInfo(MODID, null, "", tileID, null, 0, submapManager, tileID);

        infoList.add(info);
        infoMap[tileID] = info;
    }

    private IVariationInfo getClientInfo(String modid, String texture, String description, int metadata, Block block, int blockMeta, ISubmapManager customManager, int order) {
        ICarvingVariation var = CarvingUtils.getDefaultVariationFor(MANAGER.paraBlock().block(), metadata, order);
        TextureType type = TextureType.getTypeFor(null, modid, texture);
        if (type == TextureType.CUSTOM && customManager == null && block == null) {
            throw new IllegalArgumentException(String.format("Could not find texture %s, and no custom texture manager was provided.", texture));
        }

        ISubmapManager manager;
        if (customManager != null) {
            manager = customManager;
        } else if (block != null) {
            manager = type.createManagerFor(var, block, blockMeta);
        } else {
            manager = type.createManagerFor(var, texture);
        }
        return new VariationInfoBase(var, description, manager, type);
    }

    private IVariationInfo getServerInfo(String modid, String texture, String description, int metadata, Block block, int blockMeta, ISubmapManager customManager, int order) {
        ICarvingVariation var = CarvingUtils.getDefaultVariationFor(MANAGER.paraBlock().block(), metadata, order);
        return new VariationInfoBase(var, description, null);
    }

    public IVariationInfo getVariation(int metadata) {
        if (metadata < 0 || metadata > infoMap.length)
            metadata = 0;

        IVariationInfo info = infoMap[metadata];
        if (info == null)
            return null;

        return info;
    }

    public IIcon getIcon(int side, int metadata) {
        if (metadata < 0 || metadata > infoMap.length)
            metadata = 0;

        IVariationInfo info = infoMap[metadata];
        if (info == null)
            return getMissingIcon();

        return info.getIcon(side, metadata);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getMissingIcon() {
        return ((TextureMap) Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture)).getAtlasSprite("missingno");
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(Block block, IIconRegister register) {
        for (IVariationInfo info : infoList) {
            info.registerIcons(MODID, block, register);
        }
    }
}
