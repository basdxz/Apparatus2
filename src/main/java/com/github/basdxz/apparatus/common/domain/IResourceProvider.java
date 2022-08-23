package com.github.basdxz.apparatus.common.domain;

import com.github.basdxz.apparatus.common.resource.IResource;
import lombok.*;

public interface IResourceProvider {
    <RESOURCE extends IResource>
    void tryProviding(@NonNull IResourceContainer<RESOURCE> container);
}
