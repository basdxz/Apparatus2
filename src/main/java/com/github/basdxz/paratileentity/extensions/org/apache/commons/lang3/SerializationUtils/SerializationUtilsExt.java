package com.github.basdxz.paratileentity.extensions.org.apache.commons.lang3.SerializationUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import manifold.ext.rt.api.Extension;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SerializationUtilsExt {
    @SneakyThrows
    @Extension
    public static <T> T deserialize_TM(byte[] bytes) {
        val objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (T) objectInputStream.readObject();
    }
}
