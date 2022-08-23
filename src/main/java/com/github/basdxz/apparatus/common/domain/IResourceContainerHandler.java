package com.github.basdxz.apparatus.common.domain;

import lombok.*;

public interface IResourceContainerHandler {
    void resetResources();

    void loadResources(@NonNull IResourceProvider provider);

    void ensureAllResourcesLoaded();
}
