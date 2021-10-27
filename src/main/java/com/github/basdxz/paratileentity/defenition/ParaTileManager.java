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
import lombok.val;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Accessors(fluent = true)
public class ParaTileManager implements IParaTileManager {
    @Getter
    protected final Class<? extends ItemBlock> itemClass = ParaItemBlock.class;
    protected final ThreadLocal<IParaTile> nullParaTile = new ThreadLocal<>();
    protected final ThreadLocal<IParaTile> bufferTile = new ThreadLocal<IParaTile>() {
        @Override
        protected IParaTile initialValue() {
            return super.initialValue();
        }
    };

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
        this.paraTileEntity = registerTileEntity(tileEntityClass);
        paraBlock = new ParaBlock(this);
        carvingHelper = new CarvableHelperExtended(this);
    }

    protected IParaTileEntity registerTileEntity(Class<? extends IParaTileEntity> tileEntityClass) {
        try {
            val managerField = tileEntityClass.getDeclaredField("MANAGER");
            managerField.setAccessible(true);
            managerField.set(null, this);
            bufferTile.set(registerNullParaTile());
            return tileEntityClass.getDeclaredConstructor().newInstance().registerTileEntity(modid, name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException("Class extending IParaTileEntity must have a MANAGER field.");
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException e) {
            throw new IllegalStateException("Class extending IParaTileEntity didn't implement a no argument constructor. (early)");
        }
    }

    protected IParaTile registerNullParaTile() {
        nullParaTile.set(registerTile(ChiselTextureTest.builder().tileID(0).build()));
        return nullParaTile.get();
    }

    @Override
    public TileEntity createNewTileEntity(World world, int tileID) {
        return paraTileEntity.createNewTileEntity(world, tileID);
    }

    @Override
    public IParaTile registerTile(@NonNull IParaTile tile) {
        if (IParaTileManager.tileIDInvalid(tile.tileID()))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        if (tileList.get(tile.tileID()) != null)
            if (tile.tileID() == 0)
                throw new IllegalArgumentException("ID 0 is used for null block.");
            else
                throw new IllegalArgumentException("ID already taken.");

        tileList.set(tile.tileID(), tile);
        tile.init(this);
        return tile;
    }

    //TODO make null ID's refer to ID 0
    @Override
    public IParaTile paraTile(int tileID) {
        if (IParaTileManager.tileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID " + tileID + " is out of bounds.");
        if (tileList.get(tileID) == null)
            throw new IllegalArgumentException("Tile ID " + tileID + " doesn't exist.");

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
    public void bufferTile(IParaTile tile) {
        System.out.println("Written tile!");
        if (bufferTile.get() != nullParaTile.get())
            System.out.println("WARNING: Buffer Written twice!");
        bufferTile.set(tile);
    }

    @Override
    public IParaTile bufferTile() {
        System.out.println("Read tile!");
        val bufferTile = this.bufferTile.get();
        if (bufferTile == nullParaTile.get()) {
            System.out.println("WARNING: Buffer Read twice!");
        } else {
            this.bufferTile.set(nullParaTile.get());
        }
        return bufferTile;
    }
}
