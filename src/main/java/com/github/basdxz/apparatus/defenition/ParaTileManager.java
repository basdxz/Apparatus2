package com.github.basdxz.apparatus.defenition;

import com.github.basdxz.apparatus.defenition.chisel.CarvableHelperExtended;
import com.github.basdxz.apparatus.defenition.managed.IParaBlock;
import com.github.basdxz.apparatus.defenition.managed.IParaTileEntity;
import com.github.basdxz.apparatus.defenition.managed.ParaBlock;
import com.github.basdxz.apparatus.defenition.managed.ParaItemBlock;
import com.github.basdxz.apparatus.defenition.tile.BufferedParaTile;
import com.github.basdxz.apparatus.defenition.tile.IBufferedParaTile;
import com.github.basdxz.apparatus.defenition.tile.IParaTile;
import com.github.basdxz.apparatus.instance.NullTile;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import lombok.val;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import static com.github.basdxz.apparatus.ApparatusMod.warn;
import static com.github.basdxz.apparatus.defenition.IParaTileManager.tileIDInvalid;
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

    protected final Map<String, IParaTile> tileMap = new HashMap<>();
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
        val paraTileClasses = new Reflections(instancePackagePath)
                .get(TypesAnnotated.with(RegisterParaTile.class).asClass());
        for (Class<?> paraTileClass : paraTileClasses) {
            if (IParaTile.class.isAssignableFrom(paraTileClass)) {
                val annotation = paraTileClass.getAnnotation(RegisterParaTile.class);
                if (!modid.equals(annotation.modid()) || !name.equals(annotation.manager()))
                    continue;
                try {
                    val builder = paraTileClass.getMethod("builder").invoke(null);
                    val buildMethod = builder.getClass().getMethod("build");
                    buildMethod.setAccessible(true);
                    ((IParaTile) buildMethod.invoke(builder)).register(this);
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
        if (tileIDInvalid(tile.tileID()))
            throw new IllegalArgumentException("Tile ID out of bounds.");
        if (tileMap.get(tile.tileID()) != null)
            if (NULL_TILE_ID.equals(tile.tileID()))
                throw new IllegalArgumentException("ID NULL is used for null block.");
            else
                throw new IllegalArgumentException("ID " + tile.tileID() + " already taken.");

        tileMap.put(tile.tileID(), tile);
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
    public IParaTile paraTile(String tileID) {
        if (tileIDInvalid(tileID))
            throw new IllegalArgumentException("Tile ID " + tileID + " is invalid.");
        if (tileMap.get(tileID) == null)
            return nullTile.paraTile();

        return tileMap.get(tileID);
    }

    @Override
    public Iterable<IParaTile> tileList() {
        return tileMap.values();
    }

    @Override
    public Iterable<String> allTileIDs() {
        return tileMap.keySet();
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
