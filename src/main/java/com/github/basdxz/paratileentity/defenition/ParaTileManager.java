package com.github.basdxz.paratileentity.defenition;

import com.github.basdxz.paratileentity.defenition.chisel.CarvableHelperExtended;
import com.github.basdxz.paratileentity.defenition.managed.IParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.IParaTileEntity;
import com.github.basdxz.paratileentity.defenition.managed.ParaBlock;
import com.github.basdxz.paratileentity.defenition.managed.ParaItemBlock;
import com.github.basdxz.paratileentity.defenition.tile.BufferedParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IBufferedParaTile;
import com.github.basdxz.paratileentity.defenition.tile.IParaTile;
import com.github.basdxz.paratileentity.instance.NullTile;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.val;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.debug;
import static com.github.basdxz.paratileentity.ParaTileEntityMod.warn;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Accessors(fluent = true)
public class ParaTileManager implements IParaTileManager {
    protected final boolean doneLoading;

    @Getter
    protected final Class<? extends ItemBlock> itemClass = ParaItemBlock.class;
    protected final IBufferedParaTile nullTile;
    protected final ThreadLocal<IBufferedParaTile> tileBuffer;

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

    public ParaTileManager(String modid, String name, Class<? extends IParaTileEntity> tileEntityClass) {
        this.modid = modid;
        this.name = name;
        paraBlock = new ParaBlock(this);
        nullTile = bufferedNullTile();
        tileBuffer = tileBuffer();
        carvingHelper = new CarvableHelperExtended(this);
        this.paraTileEntity = registerTileEntity(tileEntityClass);
        doneLoading = true;
    }

    protected IBufferedParaTile bufferedNullTile() {
        return new BufferedParaTile(null, 0, 0, 0, initNullTile());
    }

    protected IParaTile initNullTile() {
        val nullTile = NullTile.builder().tileID(NULL_TILE_ID).build();
        registerTile(nullTile);
        return nullTile;
    }

    protected ThreadLocal<IBufferedParaTile> tileBuffer() {
        return ThreadLocal.withInitial(() -> nullTile);
    }

    protected IParaTileEntity registerTileEntity(Class<? extends IParaTileEntity> tileEntityClass) {
        try {
            val managerField = tileEntityClass.getDeclaredField("MANAGER");
            managerField.setAccessible(true);
            managerField.set(null, this);
            return tileEntityClass.getDeclaredConstructor().newInstance().registerTileEntity(modid, name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Class extending IParaTileEntity must have a MANAGER field.");
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            e.getCause().printStackTrace();
            throw new IllegalStateException("Class extending IParaTileEntity didn't implement a no argument constructor.");
        }
    }

    @Override
    public TileEntity createNewTileEntity() {
        return paraTileEntity.createNewTileEntity();
    }

    @Override
    public IParaTile registerTile(@NonNull IParaTile tile) {
        if (IParaTileManager.tileIDInvalid(tile.tileID()))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        if (tileList.get(tile.tileID()) != null)
            if (tile.tileID() == 0)
                throw new IllegalArgumentException("ID 0 is used for null block.");
            else
                throw new IllegalArgumentException("ID " + tile.tileID() + " already taken.");

        tileList.set(tile.tileID(), tile);
        tile.init(this);
        return tile;
    }

    @Override
    public IParaTile paraTile(int tileID) {
        if (IParaTileManager.tileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID " + tileID + " is out of bounds.");
        if (tileList.get(tileID) == null)
            return nullTile.paraTile();

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

    @Override
    public void bufferedTile(IBufferedParaTile bufferedTile) {
        debug("Written tile! " + bufferedTile.tileID());
        if (!bufferedTileNull())
            warn("WARNING: Buffer Written twice!");
        tileBuffer.set(bufferedTile);
    }

    @Override
    public IBufferedParaTile bufferedTile() {
        val bufferTile = tileBuffer.get();
        debug("Read tile! " + bufferTile.tileID());
        if (bufferedTileNull() && doneLoading)
            warn("WARNING: Buffer Read twice!");
        tileBuffer.remove();
        return bufferTile;
    }

    @Override
    public boolean bufferedTileNull() {
        return tileBuffer.get() == nullTile;
    }
}
