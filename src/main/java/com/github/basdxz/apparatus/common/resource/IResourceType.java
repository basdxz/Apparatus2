package com.github.basdxz.apparatus.common.resource;


//TODO: Hash/Equals/ToString
public interface IResourceType<RESOURCE extends IResource> {
    Class<RESOURCE> resourceBaseClass();

    String fileExtension();
}
