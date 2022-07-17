package com.github.basdxz.apparatus.common.render;

import lombok.*;

import java.nio.ByteBuffer;

//TODO: hash and toString
public interface IRenderBuffer<LAYOUT extends IRenderBufferLayout> {
    default boolean renderBufferEqual(@NonNull IRenderBuffer<LAYOUT> buffer) {
        return bufferName().equals(buffer.bufferName()) &&
               byteBuffer().equals(buffer.byteBuffer()) &&
               bufferInfo().renderBufferInfoEqual(buffer.bufferInfo());
    }

    default LAYOUT layout() {
        return bufferInfo().bufferLayout();
    }

    default IRenderBufferInfo<LAYOUT> bufferInfo() {
        return bufferID().bufferInfo();
    }

    default String bufferName() {
        return bufferID().bufferName();
    }

    IRenderBufferID<LAYOUT> bufferID();

    default void ensureByteBufferSize() {
        if (byteBuffer().capacity() < byteSize())
            throw new IllegalStateException("ByteBuffer to small");//TODO: Better exceptions;
        if (byteBuffer().capacity() > byteSize())
            throw new IllegalStateException("ByteBuffer to big");//TODO: Better exceptions;
    }

    default int byteSize() {
        return bufferInfo().byteSize();
    }

    ByteBuffer byteBuffer();
}
