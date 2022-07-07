package com.github.basdxz.apparatus.common.domain;

import lombok.*;

public interface IDomainRegistry {
    IDomain getDomain(@NonNull String domainName);

    void add(@NonNull IRegistry registry);
}
