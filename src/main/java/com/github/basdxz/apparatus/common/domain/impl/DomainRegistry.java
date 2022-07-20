package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.*;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static lombok.AccessLevel.PROTECTED;


//TODO: Allow lookups in the form of minecraft:stick or apparatus:red_square
@NoArgsConstructor(access = PROTECTED)
public class DomainRegistry implements IInternalDomainRegistry {
    public static final IDomainRegistry INSTANCE = new DomainRegistry();

    protected static final String DEFAULT_DOMAIN_MANAGER_NAME = "apparatus_domain_manager";

    protected final Map<String, IInternalDomain> domains = new HashMap<>();
    protected final Set<IRegistry> registries = new HashSet<>();

    @Override
    public void resetResources() {
        domains.values().forEach(IResourceContainerRegistry::resetResources);
    }

    @Override
    public void registerResources(@NonNull IResourceProvider provider) {
        domains.values().forEach(domain -> domain.registerResources(provider));
    }

    @Override
    public void ensureAllResourcesRegistered() {
        domains.values().forEach(IResourceContainerRegistry::ensureAllResourcesRegistered);
    }

    @Override
    public IDomain getDomain(@NonNull String domainName) {
        return domains.computeIfAbsent(domainName.intern(), this::newDomain);
    }

    protected IInternalDomain newDomain(@NonNull String domainName) {
        return new Domain(domainName, this);
    }

    @Override
    public void add(@NonNull IRegistry registry) {
        registries.add(registry);
    }

    @Override
    public String registryName() {
        return DEFAULT_DOMAIN_MANAGER_NAME;
    }

    @Override
    public void register(@NonNull IEntity entity) {
        registries.forEach(registry -> registry.register(entity));
    }

    @Override
    public void register(@NonNull IRecipe recipe) {
        registries.forEach(registry -> registry.register(recipe));
    }

    @Override
    public String toString() {
        return registryToString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof IRegistry))
            return false;
        return registryEquals((IRegistry) obj);
    }
}
