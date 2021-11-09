package com.github.basdxz.paratileentity.network;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;

import java.io.Serializable;
import java.util.List;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiParaTileChange implements Serializable {
    protected static ThreadLocal<MultiParaTileChange> bufferedMultiParaTileChange = new ThreadLocal<>();
    protected final List<Integer> paraTileIDs;

    public static boolean bufferedPacketNotNull() {
        return bufferedMultiParaTileChange.get() != null;
    }

    public static void bufferedMultiParaTileChange(List<Integer> paraTileIDs) {
        bufferedMultiParaTileChange(new MultiParaTileChange(paraTileIDs));
    }

    public static void bufferedMultiParaTileChange(MultiParaTileChange multiParaTileChange) {
        if (bufferedMultiParaTileChange.get() != null)
            throw new IllegalStateException("Buffered data processed too slowly!");
        bufferedMultiParaTileChange.set(multiParaTileChange);
    }

    public static MultiParaTileChange bufferedMultiParaTileChange() {
        val multiParaTileChange = bufferedMultiParaTileChange.get();
        if (multiParaTileChange == null)
            throw new IllegalStateException("No buffered data!");
        bufferedMultiParaTileChange.remove();
        return multiParaTileChange;
    }
}
