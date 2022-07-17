package com.github.basdxz.apparatus.common.render;

import lombok.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

//TODO: hash and toString
public interface IRenderBuffer<LAYOUT extends IRenderBufferLayout> {
    default boolean renderBufferEqual(@NonNull IRenderBuffer<LAYOUT> buffer) {
        return bufferName().equals(buffer.bufferName()) &&
               byteBuffer().equals(buffer.byteBuffer()) &&
               bufferInfo().renderBufferInfoEqual(buffer.bufferInfo());
    }

    default LAYOUT bufferLayout() {
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

    default void ensureIndexBounds(int index) {
        if (index < 0)
            throw new IllegalStateException("Index can't be less than 0");//TODO: Better exceptions;
        if (index > vertexCount() - 1)
            throw new IllegalStateException("Index out of range");//TODO: Better exceptions;
    }

    default int vertexCount() {
        return bufferInfo().vertexCount();
    }

    ByteBuffer byteBuffer();

    ShortBuffer shortBuffer();

    IntBuffer intBuffer();

    FloatBuffer floatBuffer();
}
