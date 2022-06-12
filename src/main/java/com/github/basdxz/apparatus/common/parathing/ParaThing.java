package com.github.basdxz.apparatus.common.parathing;


import com.github.basdxz.apparatus.common.resource.IRenderer;

public interface ParaThing {
    //TODO: ID should not be string, we should have our own interface for it since strings are final.
    String paraID();

    String localizedName();

    IRenderer renderer();
}
