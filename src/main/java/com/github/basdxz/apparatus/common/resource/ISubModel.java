package com.github.basdxz.apparatus.common.resource;

import java.util.Collections;

public interface ISubModel extends IModel {
    @Override
    default Iterable<ISubModel> subModels() {
        return Collections.emptyList();
    }
}
