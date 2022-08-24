package com.github.basdxz.apparatus.common.domain;

import lombok.*;

public interface IResourceProvider {
    void tryProviding(@NonNull IResourceContainer<?> container);
}
