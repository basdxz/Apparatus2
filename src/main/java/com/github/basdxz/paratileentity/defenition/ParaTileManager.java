package com.github.basdxz.paratileentity.defenition;

import com.github.basdxz.paratileentity.defenition.chisel.CarvableHelperExtended;
import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.github.basdxz.paratileentity.defenition.managed.ParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.ParaItemBlock;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.instance.ChiselTextureTest;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.basdxz.paratileentity.instance.ParaTileEntity.MANAGER;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Accessors(fluent = true)
public class ParaTileManager implements IParaTileManager {
    @Getter
    private final Class<? extends ItemBlock> itemClass = ParaItemBlock.class;

    protected final List<IParaTile> tileList = Arrays.asList(new IParaTile[MAX_TILE_ID + 1]);
    @Getter
    protected final String name;
    @Getter
    protected final String modid;
    @Getter
    protected final IParaBlock paraBlock;
    protected final IParaTileEntity paraTileEntity;

    @Getter
    protected final CarvableHelperExtended carvingHelper;

    public ParaTileManager(String modid, String name, IParaTileEntity paraTileEntity) {
        this.modid = modid;
        this.name = name;
        this.paraTileEntity = paraTileEntity.registerTileEntity(modid, name);
        paraBlock = new ParaBlock(this);
        carvingHelper = new CarvableHelperExtended(this);
    }

    //TODO: Register a blank error tile entity at ID 0 and hide it from NEI (keep NEI optional)
    protected void registerNullParaTile() {
        MANAGER.registerTile(ChiselTextureTest.builder().tileID(0).build());
    }

    @Override
    public TileEntity createNewTileEntity(World world, int tileID) {
        return paraTileEntity.createNewTileEntity(world, tileID);
    }

    @Override
    public void registerTile(@NonNull IParaTile tile) {
        if (IParaTileManager.tileIDInvalid(tile.tileID()))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        if (tileList.get(tile.tileID()) != null)
            throw new IllegalArgumentException("ID already taken.");

        tileList.set(tile.tileID(), tile);
        tile.init(this);
    }

    //TODO make null ID's refer to ID 0
    @Override
    public IParaTile paraTile(int tileID) {
        if (IParaTileManager.tileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        if (tileList.get(tileID) == null)
            throw new IllegalArgumentException("Tile ID doesn't exist.");

        return tileList.get(tileID);
    }

    @Override
    public Iterable<IParaTile> tileList() {
        return tileList
                .stream()
                .filter(Objects::nonNull)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
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
