package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.ILocation;
import lombok.*;
import lombok.experimental.*;

@Data
@Accessors(fluent = true, chain = true)
public class Location implements ILocation {
    @NonNull
    protected final IDomain domain;
    @NonNull
    protected final String path;
}
