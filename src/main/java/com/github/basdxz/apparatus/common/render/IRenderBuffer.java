package com.github.basdxz.apparatus.common.render;

import lombok.*;

import java.nio.ByteBuffer;

//TODO: hash
public interface IRenderBuffer {
    default boolean renderBufferEqual(@NonNull IRenderBuffer buffer) {
        return bufferName().equals(buffer.bufferName()) &&
               byteBuffer().equals(buffer.byteBuffer()) &&
               bufferInfo().renderBufferInfoEqual(buffer.bufferInfo());
    }

    IRenderBufferInfo bufferInfo();

    default String bufferName() {
        return bufferInfo().bufferName();
    }

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
