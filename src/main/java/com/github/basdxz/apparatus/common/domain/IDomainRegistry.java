package com.github.basdxz.apparatus.common.domain;

import lombok.*;

public interface IDomainRegistry extends IResourceContainerHandler {
    IDomain getDomain(@NonNull String domainName);

    void add(@NonNull IRegistry registry);
}
