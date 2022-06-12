package com.github.basdxz.apparatus.common.resource;

public interface IModel {
    IModelProperties properties();

    Iterable<ISubModel> subModels();
}
