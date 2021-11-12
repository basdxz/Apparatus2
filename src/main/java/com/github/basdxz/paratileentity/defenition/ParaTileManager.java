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
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.github.basdxz.paratileentity.ParaTileEntityMod.warn;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.reflections.scanners.Scanners.TypesAnnotated;

@Accessors(fluent = true)
public class ParaTileManager implements IParaTileManager {
    protected boolean initComplete;
    protected boolean postInitComplete;

    @Getter
    protected final Class<? extends ItemBlock> itemClass = ParaItemBlock.class;
    @Getter
    protected final IBufferedParaTile nullTile;
    protected final ThreadLocal<IBufferedParaTile> tileBuffer;

    protected final List<IParaTile> tileList = Arrays.asList(new IParaTile[MAX_TILE_ID + 1]);
    @Getter
    protected final String name;
    @Getter
    protected final String modid;
    protected final String instancePackagePath;

    @Getter
    protected final IParaBlock paraBlock;
    protected final IParaTileEntity paraTileEntity;

    @Getter
    protected final CarvableHelperExtended carvingHelper;

    public ParaTileManager(String modid, String name, String instancePackagePath,
                           Class<? extends IParaTileEntity> tileEntityClass) {
        this.modid = modid;
        this.name = name;
        this.instancePackagePath = instancePackagePath;
        paraBlock = new ParaBlock(this);
        nullTile = bufferedNullTile();
        tileBuffer = tileBuffer();
        carvingHelper = new CarvableHelperExtended(this);
        this.paraTileEntity = registerTileEntity(tileEntityClass);
        registerAnnotatedTiles();
        initComplete = true;
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

    protected void registerAnnotatedTiles() {
        Reflections reflections = new Reflections(instancePackagePath);
        Set<Class<?>> paraTileClasses = reflections.get(TypesAnnotated.with(RegisterParaTile.class).asClass());
        for (Class<?> paraTileClass : paraTileClasses) {
            if (IParaTile.class.isAssignableFrom(paraTileClass)) {
                val annotation = paraTileClass.getAnnotation(RegisterParaTile.class);
                if (!modid.equals(annotation.modid()) || !name.equals(annotation.manager()))
                    continue;
                try {
                    val builder = paraTileClass.getMethod("builder").invoke(null);
                    val buildMethod = builder.getClass().getMethod("build");
                    buildMethod.setAccessible(true);
                    val paraTile = buildMethod.invoke(builder);
                    ((IParaTile) paraTile).register(this);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalStateException("Classes annotated with @RegisterParaTile must use the Lombok @Builder or @SuperBuilder annotation.");
                }
            } else {
                throw new IllegalStateException("Classes annotated with @RegisterParaTile must implement the IParaTile interface.");
            }
        }
    }

    @Override
    public IParaTile registerTile(@NonNull IParaTile tile) {
        if (initComplete)
            throw new IllegalArgumentException("Manager already finished loading, maybe use @RegisterParaTile?");
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
    public void postInit() {
        if (postInitComplete)
            throw new IllegalStateException("Post init already complete.");
        for (IParaTile paraTile : tileList()) {
            paraTile.registerRecipes();
        }
        postInitComplete = true;
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
        if (!bufferedTileNull())
            warn("WARNING: Buffer Written twice!");
        tileBuffer.set(bufferedTile);
    }

    @Override
    public IBufferedParaTile bufferedTile() {
        val bufferTile = tileBuffer.get();
        if (bufferedTileNull() && initComplete)
            warn("WARNING: Buffer Read twice!");
        tileBuffer.remove();
        return bufferTile;
    }

    @Override
    public boolean bufferedTileNull() {
        return tileBuffer.get() == nullTile;
    }
}
