package com.github.basdxz.apparatus.common.domain;


//TODO: Hash/Equals
public interface IResourceType {
    default String resourceToString() {
        return extension();
    }

    String extension();
}
