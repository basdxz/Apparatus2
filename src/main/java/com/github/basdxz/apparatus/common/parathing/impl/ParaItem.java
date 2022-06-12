package com.github.basdxz.apparatus.common.parathing.impl;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.common.resource.IRenderer;
import lombok.*;
import lombok.experimental.*;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public class ParaItem implements IParaItem {
    @NonNull
    protected final String paraID;
    @NonNull
    protected final String localizedName;
    @NonNull
    protected final IRenderer renderer;
}
