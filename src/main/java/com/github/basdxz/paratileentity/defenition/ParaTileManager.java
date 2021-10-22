package com.github.basdxz.paratileentity.defenition;

import com.github.basdxz.paratileentity.defenition.managed.*;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.val;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Accessors(fluent = true)
public class ParaTileManager implements IParaTileManager {
    @Getter
    private final Class<? extends ItemBlock> itemClass = ParaItemBlock.class;

    protected final List<IParaTile> tileList = Arrays.asList(new IParaTile[MAX_TILE_ID + 1]);
    @Getter
    protected final String name;
    @Getter
    protected final IParaBlock paraBlock;
    @Getter
    protected final IParaTileEntity paraTileEntity;

    public ParaTileManager(String name) {
        this.name = name;
        paraBlock = new ParaBlock(this);
        paraTileEntity = newParaTileEntity();
    }

    public IParaTileEntity newParaTileEntity() {
        val thiz = this;
        val tile = new ParaTileEntity() {
            @Override
            protected Class<? extends ParaTileEntity> registerBaseClass() {
                return getClass();
            }

            @Override
            protected IParaTileManager registerManager() {
                return thiz;
            }
        };
        tile.registerTileEntity();
        return tile;
    }

    @Override
    public void registerTile(@NonNull IParaTile tile) {
        if (IParaTileManager.tileIDInvalid(tile.tileID()))
            throw new IllegalArgumentException("Tile ID out of bounds.");

        if (tileList.get(tile.tileID()) != null)
            throw new IllegalArgumentException("ID already taken.");

        tileList.set(tile.tileID(), tile);
        tile.registerManager(this);
    }

    @Override
    public IParaTile paraTile(int tileID) {
        if (IParaTileManager.tileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID out of bounds.");

        if (tileList.get(tileID) == null)
            throw new IllegalArgumentException("Tile ID doesn't exist.");

        return tileList.get(tileID);
    }

    @Override
    public Iterable<Integer> allTileIDs() {
        return IntStream
                .range(0, tileList.size())
                .filter(i -> tileList.get(i) != null)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
