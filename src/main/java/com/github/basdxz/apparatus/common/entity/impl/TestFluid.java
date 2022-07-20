package com.github.basdxz.apparatus.common.entity.impl;

import com.github.basdxz.apparatus.common.domain.IEntityID;
import com.github.basdxz.apparatus.common.entity.IFluid;
import com.github.basdxz.apparatus.common.loader.Loader;
import com.github.basdxz.apparatus.common.loader.context.IPreInitContext;
import com.github.basdxz.apparatus.common.loader.impl.EntityLoader;
import lombok.*;
import lombok.experimental.*;


@Getter
@Accessors(fluent = true, chain = true)
public class TestFluid implements IFluid {
    protected final IEntityID entityID;
    protected final String localizedName;

    public TestFluid(@NonNull IEntityID entityID, @NonNull String localizedName) {
        this.entityID = entityID;
        this.localizedName = localizedName;
    }

    @NoArgsConstructor
    @Loader(domainName = "apparatus")
    public static class TileLoader extends EntityLoader<TestFluid> {
        @Override
        public void preInit(@NonNull IPreInitContext<TestFluid> context) {
            context.register(new TestFluid(context.entityID("woag_my_fluid"), "Woag My Fluid??!?!"));
        }
    }
}
