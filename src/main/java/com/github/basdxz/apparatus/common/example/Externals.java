package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.impl.Domain;
import com.github.basdxz.apparatus.common.registry.IParaRegistry;
import com.github.basdxz.apparatus.common.registry.impl.ParaRegistryStub;
import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Externals {
    public static final String MINECRAFT_NAME = "minecraft";
    public static final IDomain MINECRAFT_DOMAIN = Domain.get(MINECRAFT_NAME);
    public static final IParaRegistry MINECRAFT_REGISTRY = new ParaRegistryStub(MINECRAFT_DOMAIN, MINECRAFT_NAME);
}
