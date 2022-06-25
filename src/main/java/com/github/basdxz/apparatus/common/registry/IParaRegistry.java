package com.github.basdxz.apparatus.common.registry;


import com.github.basdxz.apparatus.common.domain.IDomain;
import com.github.basdxz.apparatus.common.parathing.IParaThing;
import lombok.*;

import java.util.Optional;

public interface IParaRegistry {
    IDomain domain();

    String registryName();

    String loadersPackage();

    Optional<IParaThing> paraThing(@NonNull IParaID paraID);

    Iterable<IParaThing> paraThings();
}
