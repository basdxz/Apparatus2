package com.github.basdxz.apparatus.common.render;

//TODO: hash and equals
public interface IRenderBufferID<LAYOUT extends IRenderBufferLayout> {
    default int byteSize() {
        return bufferInfo().byteSize();
    }

    default LAYOUT bufferLayout() {
        return bufferInfo().bufferLayout();
    }

    IRenderBufferInfo<LAYOUT> bufferInfo();

    String bufferName();
}
