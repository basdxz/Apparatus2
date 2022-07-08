package com.github.basdxz.apparatus.common.domain.impl;

import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.domain.IDomainRegistry;
import com.github.basdxz.apparatus.common.domain.IInternalDomainRegistry;
import com.github.basdxz.apparatus.common.domain.IRegistry;
import com.github.basdxz.apparatus.common.entity.IEntity;
import com.github.basdxz.apparatus.common.recipe.IRecipe;
import com.github.basdxz.apparatus.common.resourceold.IResource;
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

    protected final Map<String, IDomain> domains = new HashMap<>();
    protected final Set<IRegistry> registries = new HashSet<>();

    @Override
    public IDomain getDomain(@NonNull String domainName) {
        return domains.computeIfAbsent(domainName.intern(), this::newDomain);
    }

    protected IDomain newDomain(@NonNull String domainName) {
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
    public void register(@NonNull IResource resource) {
        registries.forEach(registry -> registry.register(resource));
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
