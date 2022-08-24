package com.github.basdxz.apparatus.adapter.resource;

import com.github.basdxz.apparatus.common.domain.ILocation;
import com.github.basdxz.apparatus.common.domain.IResourceContainer;
import com.github.basdxz.apparatus.common.domain.IResourceProvider;
import com.github.basdxz.apparatus.common.resource.IResource;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

public abstract class ResourceProviderAdapter<RESOURCE extends IResource> implements IResourceProvider {
    protected final Map<ILocation<RESOURCE>, RESOURCE> resources = new HashMap<>();

    @Override
    public void tryProviding(@NonNull IResourceContainer<?> container) {
        if (isSupported(container)) {
            val supportedContainer = castResourceContainer(container);
            supportedContainer.resource(resource(supportedContainer));
        }
    }

    protected abstract boolean isSupported(@NonNull IResourceContainer<?> container);

    @SuppressWarnings("unchecked")
    protected IResourceContainer<RESOURCE> castResourceContainer(@NonNull IResourceContainer<?> container) {
        return (IResourceContainer<RESOURCE>) container;
    }

    protected RESOURCE resource(@NonNull IResourceContainer<RESOURCE> container) {
        return resources.computeIfAbsent(container.location(), this::newResource);
    }

    protected abstract RESOURCE newResource(ILocation<RESOURCE> location);
}
