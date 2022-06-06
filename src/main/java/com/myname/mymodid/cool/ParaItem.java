package com.myname.mymodid.cool;

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
}
