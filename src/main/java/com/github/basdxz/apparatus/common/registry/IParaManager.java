package com.github.basdxz.apparatus.common.registry;

import com.github.basdxz.apparatus.common.loader.IInitializeable;
import com.github.basdxz.apparatus.common.parathing.IParaThing;

import java.util.Map;

public interface IParaManager extends IParaRegistry, IInitializeable {
    String loadersPackage();

    Map<IParaID, IParaThing> paraThingsMap();
}
