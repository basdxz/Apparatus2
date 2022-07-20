package com.github.basdxz.apparatus.common.resource;


//TODO: Hash/Equals
public interface IResourceType {
    default String resourceTypeToString() {
        return extension();
    }

    String extension();
}
