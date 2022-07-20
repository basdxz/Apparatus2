package com.github.basdxz.apparatus.common.domain;

import lombok.*;

public interface IResourceContainerRegistry {
    void resetResources();

    void registerResources(@NonNull IResourceProvider provider);

    void ensureAllResourcesRegistered();
}
