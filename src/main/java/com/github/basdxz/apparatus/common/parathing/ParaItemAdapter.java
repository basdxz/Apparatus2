package com.github.basdxz.apparatus.common.parathing;

import lombok.*;
import lombok.experimental.*;

@Getter
@RequiredArgsConstructor
@Accessors(fluent = true, chain = true)
public class ParaItemAdapter implements ParaItem {
    @NonNull
    protected final String paraID;
    @NonNull
    protected final String localizedName;
}
