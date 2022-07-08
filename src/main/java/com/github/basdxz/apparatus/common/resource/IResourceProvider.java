package com.github.basdxz.apparatus.common.resource;

import com.github.basdxz.apparatus.common.domain.ILocation;
import lombok.*;

import java.util.Optional;

public interface IResourceProvider {
    Optional<IResource> resource(@NonNull ILocation location);
}
