package com.github.basdxz.apparatus.common.registry;


import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import lombok.*;

import java.util.Optional;

public interface IParaRegistry {
    String registryName();

    String classPath();

    IDomain domain();

    Optional<IParaThing> paraThing(@NonNull IParaID paraID);

    Iterable<IParaThing> paraThings();
}
