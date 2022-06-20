package com.github.basdxz.apparatus.common.example;

import com.github.basdxz.apparatus.common.domain.impl.Domain;
import com.github.basdxz.apparatus.common.domain.impl.IDomain;
import lombok.*;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ExternalDomains {
    public static final IDomain MINECRAFT = new Domain("minecraft");
}

