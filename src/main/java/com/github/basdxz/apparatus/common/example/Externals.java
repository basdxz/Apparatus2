package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.impl.DomainRegistry;
import lombok.experimental.*;

@UtilityClass
public final class Externals {
    public static final String MINECRAFT_NAME = "minecraft";
    public static final IDomain MINECRAFT_DOMAIN = DomainRegistry.INSTANCE.getDomain(MINECRAFT_NAME);
}
