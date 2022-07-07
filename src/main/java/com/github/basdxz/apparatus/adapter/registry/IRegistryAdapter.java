package com.github.basdxz.apparatus.adapter.registry;

import com.github.basdxz.apparatus.adapter.entity.IEntityAdapter;
import com.github.basdxz.apparatus.common.domain.IRegistry;

import java.util.List;

public interface IRegistryAdapter extends IRegistry {
    List<IEntityAdapter> entityAdapters();
}
