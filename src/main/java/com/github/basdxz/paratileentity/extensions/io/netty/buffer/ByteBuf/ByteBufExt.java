package com.github.basdxz.paratileentity.extensions.io.netty.buffer.ByteBuf;

import io.netty.buffer.ByteBuf;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.var;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.Arrays;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ByteBufExt {
    @SneakyThrows
    public static <T> T readSerializeable(@This ByteBuf thiz) {
        var bytes = thiz.array();
        bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
        return (T) SerializationUtils.deserialize_TM(bytes);
    }

    public static void writeSerializeable(@This ByteBuf thiz, final Serializable data) {
        thiz.writeBytes(SerializationUtils.serialize(data));
    }
}
