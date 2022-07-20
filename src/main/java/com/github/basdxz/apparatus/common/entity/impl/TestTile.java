package com.github.basdxz.apparatus.common.entity.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.ITile;
import com.github.basdxz.apparatus.common.loader.Loader;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.impl.EntityLoader;
import lombok.*;
import lombok.experimental.*;


@Getter
@Accessors(fluent = true, chain = true)
public class TestTile implements ITile {
    protected final IEntityID entityID;
    protected final String localizedName;

    public TestTile(@NonNull IEntityID entityID, @NonNull String localizedName) {
        this.entityID = entityID;
        this.localizedName = localizedName;
    }

    @NoArgsConstructor
    @Loader(domainName = "apparatus")
    public static class TileLoader extends EntityLoader<TestTile> {
        @Override
        public void preInit(@NonNull IPreInitContext<TestTile> context) {
            context.register(new TestTile(context.entityID("woag_my_block"), "Woag My Block"));
        }
    }
}
